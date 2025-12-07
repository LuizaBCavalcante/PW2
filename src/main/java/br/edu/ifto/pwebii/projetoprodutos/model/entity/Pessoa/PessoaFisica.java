package br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaFisica extends Pessoa implements Serializable {
    private String nome, cpf;

    public String getNomeCliente() {

        return nome;
    }

    public String getNome() {
        return nome;
    }

    @Override
    public boolean tipoObjeto(String tipo) {
        if("pessoaFisica".equals(tipo)) {
            return true;
        }
        return false;
    }
    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }
}
