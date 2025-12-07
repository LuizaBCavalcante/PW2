package br.edu.ifto.pwebii.projetoprodutos.model.Repository;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.PessoaJuridica;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaJuridicaRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(PessoaJuridica pj){

        em.persist(pj);
    }
    public PessoaJuridica pessoa(Long id){

        return em.find(PessoaJuridica.class, id);
    }
    public List<PessoaJuridica> pessoas(){
        Query query = em.createQuery("from PessoaJuridica ");
        return query.getResultList();
    }
    public void remove(Long id){
        PessoaJuridica pj = em.find(PessoaJuridica.class, id);
        em.remove(pj);
    }
    public void update(PessoaJuridica pj){
        em.merge(pj);
    }
}

