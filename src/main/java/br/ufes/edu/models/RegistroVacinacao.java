package br.ufes.edu.models;

import br.ufes.edu.dao.RegistroVacinacaoDao;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
public class RegistroVacinacao {

    private Long idRegistro;
    private String cpf;
    private Long idVacina;
    private Date dataAdministracao;
    private int dosesTomadas;

    public RegistroVacinacao aumentaDose() {
        this.dosesTomadas++;
        return this;
    }
}
