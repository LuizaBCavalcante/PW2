package br.edu.ifto.pwebii.projetoprodutos.model.Repository;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.Pessoa;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.PessoaFisica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaFisicaRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(PessoaFisica pf){

        em.persist(pf);
    }
    public PessoaFisica pessoa(Long id){

        return em.find(PessoaFisica.class, id);
    }

    public List<PessoaFisica> pessoas(){
        Query query = em.createQuery("from PessoaFisica");
        return query.getResultList();
    }
    public void remove(Long id){
        PessoaFisica pf = em.find(PessoaFisica.class, id);
        em.remove(pf);
    }
    public void update(PessoaFisica pf){

        em.merge(pf);
    }
}

