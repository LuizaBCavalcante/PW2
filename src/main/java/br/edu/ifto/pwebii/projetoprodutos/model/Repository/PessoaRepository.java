package br.edu.ifto.pwebii.projetoprodutos.model.Repository;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.Pessoa;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class PessoaRepository {

    @PersistenceContext
    private EntityManager em;

    public List<Pessoa> pessoas(String nome){
        String hql = "SELECT p FROM Pessoa p";
        if(nome != null && !nome.isEmpty()){
            hql +=  " WHERE lower(treat(p as PessoaFisica).nome) LIKE lower(:nome) "+
                    " OR lower(treat(p as PessoaJuridica).razaoSocial) LIKE lower(:nome)";
        }
        Query query = em.createQuery(hql);
        if(nome != null && !nome.isEmpty()){
            query.setParameter("nome", "%" + nome + "%");
        }
        return  query.getResultList();
    }

    public Pessoa pessoa(Long id) {
        return em.find(Pessoa.class, id);
    }
}
