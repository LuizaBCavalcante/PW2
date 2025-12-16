package br.edu.ifto.pwebii.projetoprodutos.controller;

import br.edu.ifto.pwebii.projetoprodutos.model.Repository.PessoaFisicaRepository;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.PessoaFisica;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("pessoafisica")
public class PessoaFisicaController {

    @Autowired
    private PessoaFisicaRepository repository;

    @GetMapping("/form")
    public ModelAndView form(PessoaFisica pessoaFisica, ModelMap model){
        model.addAttribute("pessoa", pessoaFisica);
        return new ModelAndView("pessoa/form");
    }

    @Transactional
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("pessoa") PessoaFisica pessoaFisica, BindingResult result){
        if(result.hasErrors()){
            return new ModelAndView("/pessoa/form");
        }
        repository.save(pessoaFisica);
        return new ModelAndView("redirect:/clientes/list");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", repository.pessoa(id));
        return new ModelAndView("pessoa/form", model);
    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView update(@Valid @ModelAttribute("pessoa") PessoaFisica pessoaFisica, BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("/pessoa/form");

        }
        repository.update(pessoaFisica);
        return new ModelAndView("redirect:/clientes/list");
    }
}
