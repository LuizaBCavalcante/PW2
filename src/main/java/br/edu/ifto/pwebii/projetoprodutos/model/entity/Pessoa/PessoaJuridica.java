package br.edu.ifto.pwebii.projetoprodutos.model.entity.Pessoa;

import jakarta.persistence.Entity;
import jakarta.persistence.PrimaryKeyJoinColumn;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;

@Entity
@PrimaryKeyJoinColumn(name = "id_pessoa")
public class PessoaJuridica extends Pessoa implements Serializable {

    @NotBlank
    private String razaoSocial;
    @NotBlank
    @Size(min = 14, max = 18)
    private String cnpj;
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
