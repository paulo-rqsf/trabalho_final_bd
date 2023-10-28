package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.SimpleDateFormat;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class User {

    private String nome;
    private String cpf;
    private String numeroSus;
    private String nomeSocial;
    private String dataNascimento;
    private String sexo;
    private String nomeMae;
    private String telefone;
    private String email;
    private String senha;
    private Address address;

    public User() {
    }

    public User(Scanner cidadaoCsv) {
        this.nome = cidadaoCsv.next();
        this.nomeSocial = cidadaoCsv.next();
        this.dataNascimento = cidadaoCsv.next();
        this.sexo = cidadaoCsv.next();
        this.nomeMae = cidadaoCsv.next();
        this.telefone = cidadaoCsv.next();
        this.email = cidadaoCsv.next();
        this.address.setCep(cidadaoCsv.next());
        this.address.setLogradouro(cidadaoCsv.next());
        this.address.setBairro(cidadaoCsv.next());
        this.address.setNumero(cidadaoCsv.nextInt());
        this.address.setComplemento(cidadaoCsv.next());
        this.address.setCidade(cidadaoCsv.next());
        this.address.setUf(cidadaoCsv.next());
    }

}
