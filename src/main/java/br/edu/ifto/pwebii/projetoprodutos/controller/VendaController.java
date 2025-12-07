package br.edu.ifto.pwebii.projetoprodutos.controller;

import br.edu.ifto.pwebii.projetoprodutos.model.Repository.PessoaRepository;
import br.edu.ifto.pwebii.projetoprodutos.model.Repository.VendaRepository;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Venda;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Produto;
import br.edu.ifto.pwebii.projetoprodutos.model.Repository.ProdutoRepository;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.LocalDate;
import java.util.List;

@Transactional
@Controller
@RequestMapping("/venda")
public class VendaController {

    @Autowired
    VendaRepository vendaRepository;

    @Autowired
    PessoaRepository pessoaRepository;

    @GetMapping("/detail/{id}")
    public String mostraDetalhes(@PathVariable("id") Long id, Model model){
        Venda venda = vendaRepository.venda(id);

        model.addAttribute("venda", venda);

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

