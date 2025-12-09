package br.edu.ifto.pwebii.projetoprodutos.controller;

import br.edu.ifto.pwebii.projetoprodutos.model.Repository.PessoaRepository;
import br.edu.ifto.pwebii.projetoprodutos.model.Repository.VendaRepository;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.ItemVenda;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.Pessoa;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Venda;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Produto;
import br.edu.ifto.pwebii.projetoprodutos.model.Repository.ProdutoRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Transactional
@Controller
@Scope("request")
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    PessoaRepository pessoaRepository;
    @Autowired
    ProdutoRepository produtoRepository;

    @Autowired
    Venda venda;

    @GetMapping("/carrinho")
    public ModelAndView carrinho(ModelMap model) {
        model.addAttribute("venda", venda);
        model.addAttribute("clientes", pessoaRepository.pessoas(null));
        return new ModelAndView("carrinho/lista");
    }

    @GetMapping("/adicionar/{id}")
    public ModelAndView adicionarItem(@PathVariable("id") Long idProduto) {
        Produto produto = produtoRepository.produto(idProduto);

        boolean existe = false;
        for (ItemVenda item : venda.getItemVendas()) {
            if (item.getProduto().getId().equals(idProduto)) {
                item.setQuantidade(item.getQuantidade() + 1.0);
                existe = true;
                break;
            }
        }

        if (!existe) {
            ItemVenda item = new ItemVenda();
            item.setProduto(produto);
            item.setValor(produto.getValor());
            item.setQuantidade(1.0);
            item.setVenda(venda);
            venda.getItemVendas().add(item);
        }

        return new ModelAndView("redirect:/venda/carrinho");
    }

    @GetMapping("/remove/{index}")
    public ModelAndView removerItem(@PathVariable("index") int index) {
        venda.getItemVendas().remove(index);
        return new ModelAndView("redirect:/venda/carrinho");
    }

    @PostMapping("/finalizar")
    public ModelAndView finalizar(@RequestParam("clienteId") Long clienteId, HttpSession session) {
        Pessoa cliente = pessoaRepository.pessoa(clienteId);

        Venda novaVenda = new Venda();
        novaVenda.setData(LocalDateTime.now());
        novaVenda.setCliente(cliente);

        List<ItemVenda> itensParaSalvar = new ArrayList<>();

        for (ItemVenda itemSessao : venda.getItemVendas()) {
            ItemVenda novoItem = new ItemVenda();
            novoItem.setProduto(itemSessao.getProduto());
            novoItem.setValor(itemSessao.getValor());
            novoItem.setQuantidade(itemSessao.getQuantidade());

            novoItem.setVenda(novaVenda);

            itensParaSalvar.add(novoItem);
        }

        novaVenda.setItemVendas(itensParaSalvar);

        vendaRepository.save(novaVenda);

        session.removeAttribute("venda");
        venda.setItemVendas(new ArrayList<>());

        return new ModelAndView("redirect:/venda/list");
    }

    @GetMapping("/detail/{id}")
    public String mostraDetalhes(@PathVariable("id") Long id, Model model){
        Venda vendaBanco = vendaRepository.venda(id);
        model.addAttribute("venda", vendaBanco);
        return "venda/detail";
    }

    @GetMapping("/list")
    public ModelAndView listVenda(
            @RequestParam(value = "data", required = false)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataFiltro,
            @RequestParam(value = "clienteId", required = false) Long clienteId,
            ModelMap model){

        model.addAttribute("lista_venda_bd", vendaRepository.vendas(dataFiltro, clienteId));
        model.addAttribute("dataFiltro", dataFiltro);
        model.addAttribute("clienteIdSelecionado", clienteId);
        model.addAttribute("lista_clientes", pessoaRepository.pessoas(null));

        return new ModelAndView("venda/list");
    }
}



