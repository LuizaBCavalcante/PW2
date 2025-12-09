package br.edu.ifto.pwebii.projetoprodutos.model.entity;

import jakarta.persistence.*;

import java.io.Serializable;
import java.math.BigDecimal;


@Entity
public class ItemVenda implements Serializable {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private Long id;
    private Double quantidade;
    private BigDecimal valor;

    @ManyToOne
    @JoinColumn(name = "id_produto")
    private Produto produto;

    @ManyToOne
    @JoinColumn(name = "id_venda")
    private Venda venda;

    public BigDecimal total(){
        if (quantidade == null || valor == null) {
            return BigDecimal.ZERO;
        }
        BigDecimal qtdBD = new BigDecimal(quantidade.toString());
        return this.valor.multiply(qtdBD);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Double quantidade) {
        this.quantidade = quantidade;
    }

    public Produto getProduto() {
        return produto;
    }

    public void setProduto(Produto produto) {
        this.produto = produto;
    }

    public Venda getVenda() {
        return venda;
    }

    public void setVenda(Venda venda) {
        this.venda = venda;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }
}