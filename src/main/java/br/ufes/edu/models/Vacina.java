package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Vacina {
    private Long idVacina;
    private String nome, descricao, lote, fabricante;
    private Date dataValidade;
    private int quantidadeDoses, intervaloDoses;
}
