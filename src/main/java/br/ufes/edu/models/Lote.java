package br.ufes.edu.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class Lote
{
    private Long codigoLote;
    private String cnpjFabricante, nomeFabricante;
    private int quantidadeVacinas;
    private Date dataFabricacao;

}
