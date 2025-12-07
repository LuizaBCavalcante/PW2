package br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa;

import br.edu.ifto.pwebii.projetoprodutos.model.entity.Venda;
import jakarta.persistence.*;

import java.io.Serializable;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Pessoa implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email, telefone;

    @OneToMany(mappedBy = "cliente")
    private List<Venda> vendas;

    public abstract String getNomeCliente();
    public abstract boolean tipoObjeto(String tipo);

    public String nomeClasse(){
        return this.getClass().getSimpleName().toLowerCase();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public List<Venda> getVendas() {
        return vendas;
    }

    public void setVendas(List<Venda> vendas) {
        this.vendas = vendas;
    }
}
