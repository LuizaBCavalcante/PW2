package br.edu.ifto.pwebii.projetoprodutos.controller;

import br.edu.ifto.pwebii.projetoprodutos.model.Repository.PessoaJuridicaRepository;
import br.edu.ifto.pwebii.projetoprodutos.model.Repository.PessoaRepository;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.PessoaFisica;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.PessoaJuridica;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
@RequestMapping("pessoajuridica")
public class PessoaJuridicaController {

    @Autowired
    PessoaJuridicaRepository repository;

    @GetMapping("/form")
    public ModelAndView form(PessoaJuridica pessoaJuridica, ModelMap model){
        model.addAttribute("pessoa", pessoaJuridica);
        return new ModelAndView("pessoa/form");
    }

    @Transactional
    @PostMapping("/save")
    public ModelAndView save(@Valid @ModelAttribute("pessoa") PessoaJuridica pessoaJuridica, BindingResult resut){
        if(resut.hasErrors()){
            return new ModelAndView("pessoa/form");
        }
        repository.save(pessoaJuridica);
        return new ModelAndView("redirect:/clientes/list");
    }
    @GetMapping("/edit/{id}")
    public ModelAndView edit(@PathVariable("id") Long id, ModelMap model) {
        model.addAttribute("pessoa", repository.pessoa(id));
        return new ModelAndView("pessoa/form", model);
    }

    @Transactional
    @PostMapping("/update")
    public ModelAndView update(@Valid PessoaJuridica pessoaJuridica, BindingResult result) {
        if(result.hasErrors()){
            return new ModelAndView("pessoa/form");
        }
        repository.update(pessoaJuridica);
        return new ModelAndView("redirect:/clientes/list");
    }
}
