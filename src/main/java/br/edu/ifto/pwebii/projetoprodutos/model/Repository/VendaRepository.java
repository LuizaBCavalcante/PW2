package br.edu.ifto.pwebii.projetoprodutos.model.Repository;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Produto;
import br.edu.ifto.pwebii.projetoprodutos.model.entity.Venda;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class VendaRepository {
    @PersistenceContext
    private EntityManager em;

    public void save(Venda venda){

        em.persist(venda);
    }

    public Venda venda(Long id){

        return em.find(Venda.class, id);
    }
    public List<Venda> vendas(LocalDate dataFiltro, Long clienteId) {

        String hql = "FROM Venda v JOIN FETCH v.cliente WHERE 1=1";
        if (dataFiltro != null) {
            hql += " AND cast(v.data as date) = :dataParam";
        }
        if (clienteId != null) {
            hql += " AND v.cliente.id = :clienteParam";
        }
        Query query = em.createQuery(hql);

        if (dataFiltro != null) {
            query.setParameter("dataParam", dataFiltro);
        }
        if (clienteId != null) {
            query.setParameter("clienteParam", clienteId);
        }

        return query.getResultList();
    }
    public void remove(Long id){
        Venda venda = em.find(Venda.class, id);
        em.remove(venda);
    }

    public void update(Venda venda){
        em.merge(venda);
    }

}
