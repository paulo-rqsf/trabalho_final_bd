package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Endereco
{

    private String cep;
    private String logradouro;
    private int numero;
    private String bairro;
    private String cidade;
    private String uf;
    private String complemento;

    public Endereco() {}
}
