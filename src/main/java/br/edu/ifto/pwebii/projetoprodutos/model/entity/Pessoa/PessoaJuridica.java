package br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaJuridica extends Pessoa implements Serializable {

    private String razaoSocial, cnpj;
    @Override
    public String getNomeCliente() {
        return razaoSocial;
    }
    @Override
    public boolean tipoObjeto(String tipo) {
        if("pessoaJuridica".equals(tipo)){
            return true;
        }
        return false;
    }
    public String getRazaoSocial() {
        return razaoSocial;
    }

    public void setRazaoSocial(String razaoSocial) {
        this.razaoSocial = razaoSocial;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        this.cnpj = cnpj;
    }
}
