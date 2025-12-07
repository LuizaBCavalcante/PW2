package br.edu.ifto.pwebii.projetoprodutos.model.entity;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa.Pessoa;
import jakarta.persistence.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Component
@Scope("session")
public class Venda implements Serializable {
    @Id
    @GeneratedValue
    private Long id;
    private LocalDateTime data;

    @OneToMany(mappedBy = "venda", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ItemVenda> itemVendas = new ArrayList<>();

    @ManyToOne
    @JoinColumn(name="id_pessoa")
    private Pessoa cliente;

    public Double total() {
        BigDecimal soma = BigDecimal.ZERO;

        for (ItemVenda item : itemVendas) {
            soma = soma.add(item.total());
        }
        return soma.doubleValue();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public List<ItemVenda> getItemVendas() {
        return itemVendas;
    }

    public void setItemVendas(List<ItemVenda> itemVendas) {
        this.itemVendas = itemVendas;
    }

    public Pessoa getCliente() {
        return cliente;
    }

    public void setCliente(Pessoa cliente) {
        this.cliente = cliente;
    }
}
