package br.edu.ifto.pwebii.projetoprodutos.controller;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Venda;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Produto;
import br.edu.ifto.pwebii.projetoprodutos.model.Repository.ProdutoRepository;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Transactional
@Controller
@RequestMapping("/produto")
public class ProdutoController {

    @Autowired
    ProdutoRepository produtoRepository;

    @GetMapping("/home")
    public ModelAndView home(ModelMap model){
        model.addAttribute("produtos", produtoRepository.produtos(null));
        return new ModelAndView("vitrine/index");
    }

    @GetMapping("/form")
    public ModelAndView form(Produto produto){
        return new ModelAndView("/produto/form");
    }

    @GetMapping("/list")
    public ModelAndView list(
    @RequestParam(value = "produto", required = false) String produto,
    @RequestParam(value = "valorMinimo", required = false) Double valorMinimo,
    ModelMap model){

        if(valorMinimo != null){
            model.addAttribute("lista_produtos_bd",produtoRepository.produtoPrecoMinimo(valorMinimo));
        }else{
            model.addAttribute("lista_produtos_bd",produtoRepository.produtos(produto));
        }
        model.addAttribute("produtoParam", produto);
        model.addAttribute("valorMinimoParam", valorMinimo);
        return new ModelAndView("/produto/list");
    }


    @PostMapping("/save")
    public ModelAndView save(Produto produto){
        produtoRepository.save(produto);
        return new ModelAndView("redirect:/produto/list");
    }

    @GetMapping("/remove/{id}")
    public ModelAndView remove(@PathVariable("id") Long id){
        produtoRepository.remove(id);
        return new ModelAndView("redirect:/produto/list");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("produto", produtoRepository.produto(id));
        return new ModelAndView("produto/form", model);
    }

    @PostMapping("/update")
    public ModelAndView update(Produto produto) {
        produtoRepository.update(produto);
        return new ModelAndView("redirect:/produto/list");
    }
}