package br.edu.ifto.pwebii.projetoprodutos.controller;

import br.edu.ifto.pwebii.projetoprodutos.model.Repository.PessoaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("clientes")
public class PessoaController {
    @Autowired
    private PessoaRepository repository;

    @GetMapping("/list")
    public ModelAndView list(
            @RequestParam(value = "nome", required = false) String nome,
            ModelMap model){
        model.addAttribute("lista_pessoas_bd",repository.pessoas(nome));
        model.addAttribute("nomeFiltro", nome);
        return new ModelAndView("/pessoa/list");
    }
}
