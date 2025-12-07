package br.edu.ifto.pwebii.projetoprodutos.model.Repository;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Produto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public class ProdutoRepository {

    @PersistenceContext
    private EntityManager em;

    public void save(Produto produto){

        em.persist(produto);
    }

    public Produto produto(Long id){

        return em.find(Produto.class, id);
    }

    public List<Produto> produtos(String produto){
        String hql = "SELECT p FROM Produto p";

        if(produto != null && !produto.isEmpty()){
            hql +=  " WHERE lower(p.descricao) LIKE lower(:produtoParam)";

        }
        Query query = em.createQuery(hql);
        if(produto != null && !produto.isEmpty()){
            query.setParameter("produtoParam", "%" + produto + "%");
        }
        return query.getResultList();
    }

    public List<Produto> produtoPrecoMinimo(Double valorMinimo){
        String hql = "FROM Produto p WHERE p.valor >= :preco ORDER BY p.valor DESC";
        Query query = em.createQuery(hql);
        query.setParameter("preco", valorMinimo);
        return query.getResultList();
    }

    public void remove(Long id){
        Produto p = em.find(Produto.class, id);
        em.remove(p);
    }

    public void update(Produto produto){
        em.merge(produto);
    }
}
