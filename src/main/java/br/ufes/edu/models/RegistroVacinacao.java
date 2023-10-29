package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class RegistroVacinacao {

    private int idRegistro;
    private String numeroSus;
    private int idVacina;
    private Date dataAdministracao;

}
