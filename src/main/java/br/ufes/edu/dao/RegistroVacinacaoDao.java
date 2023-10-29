package br.ufes.edu.dao;


import br.ufes.edu.models.RegistroVacinacao;
import br.ufes.edu.models.Vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RegistroVacinacaoDao
{
    private Connection connection;

    public RegistroVacinacaoDao(Connection connection){
        this.connection = connection;
    }

    public List<RegistroVacinacao> readAll() {
        String sql = "SELECT * FROM Registro_Vacinacao";
        List<RegistroVacinacao> registros = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            return fillList(registros, pstm);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegistroVacinacao> lerRegistrosVacinacao(String numSus) {
        String sql = "SELECT * FROM Registro_Vacinacao WHERE NUM_SUS = ?";
        List<RegistroVacinacao> registros = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, numSus);
            pstm.execute();

            return fillList(registros, pstm);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<RegistroVacinacao> readId(Long id){
        String sql = "SELECT * FROM Registro_Vacinacao WHERE ID_REGISTRO = ?";
        List<RegistroVacinacao> registros = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.execute();

            return fillList(registros, pstm);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RegistroVacinacao> fillList(List<RegistroVacinacao> registros, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                RegistroVacinacao registro = new RegistroVacinacao(
                        rst.getInt(1),
                        rst.getString(2),
                        rst.getInt(3),
                        rst.getDate(4)
                );
                registros.add(registro);
            }

            return registros;
        }
    }
}
