package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Address
{

    private String cep;
    private String logradouro;
    private String bairro;
    private String cidade;
    private String complemento;
    private String uf;
    private int numero;

    public Address() {}
}
