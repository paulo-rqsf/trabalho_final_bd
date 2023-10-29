package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Scanner;

@Getter
@Setter
@AllArgsConstructor
public class Morador {

    private String nome;
    private String email;
    private String senha;
    private String cpf;
    private String numeroSus;
    private String nomeSocial;
    private Date dataNascimento;
    private String sexo;
    private String nomeMae;
    private String telefone;
    private String estadoCivil;
    private String escolaridade;
    private String etnia;
    private boolean temPlanoSaude;
    private boolean admin;
    private Endereco endereco;

    public Morador() {
    }

    public Morador(Scanner cidadaoCsv) throws ParseException {
        this.nome = cidadaoCsv.next();
        this.nomeSocial = cidadaoCsv.next();
        DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        this.dataNascimento = formatter.parse(cidadaoCsv.next());
        this.sexo = cidadaoCsv.next();
        this.nomeMae = cidadaoCsv.next();
        this.telefone = cidadaoCsv.next();
        this.email = cidadaoCsv.next();
        this.endereco.setCep(cidadaoCsv.next());
        this.endereco.setLogradouro(cidadaoCsv.next());
        this.endereco.setBairro(cidadaoCsv.next());
        this.endereco.setNumero(cidadaoCsv.nextInt());
        this.endereco.setComplemento(cidadaoCsv.next());
        this.endereco.setCidade(cidadaoCsv.next());
        this.endereco.setUf(cidadaoCsv.next());
    }

    public java.sql.Date getDataNascimentoSql() {
        return new java.sql.Date(dataNascimento.getTime());
    }

}
