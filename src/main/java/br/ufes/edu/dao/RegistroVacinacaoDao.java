package br.ufes.edu.dao;


import br.ufes.edu.models.RegistroVacinacao;
import br.ufes.edu.models.Vacina;

import java.sql.*;
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
        String sql = "SELECT * FROM Registro_Vacinacao WHERE CPF = ?";
        List<RegistroVacinacao> registros = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, numSus);
            pstm.execute();

            return fillList(registros, pstm);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(RegistroVacinacao registro) {
        String sql = "INSERT INTO Registro_Vacinacao (ID_REGISTRO, CPF, ID_VACINA, DATA_ADMINISTRACAO, DOSES_TOMADAS) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, registro.getIdRegistro());
            pstm.setString(2, registro.getCpf());
            pstm.setLong(3, registro.getIdVacina());
            pstm.setDate(4,  new Date(registro.getDataAdministracao().getTime()));
            pstm.setInt(5, registro.getDosesTomadas());
            pstm.execute();

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void update(RegistroVacinacao registro) {
        String sql = "UPDATE Registro_Vacinacao SET DOSES_TOMADAS = ? WHERE ID_REGISTRO = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setInt(1, registro.getDosesTomadas());
            pstm.setLong(2, registro.getIdRegistro());
            pstm.execute();

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public RegistroVacinacao readId(Long id){
        String sql = "SELECT * FROM Registro_Vacinacao WHERE ID_REGISTRO = ?";
        RegistroVacinacao registro = null;

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()){
                if (rst.next()) {
                    registro = new RegistroVacinacao(
                            rst.getLong(1),
                            rst.getString(2),
                            rst.getLong(3),
                            rst.getDate(4),
                            rst.getInt(5)
                    );
                }
            }
            return registro;

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<RegistroVacinacao> fillList(List<RegistroVacinacao> registros, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                RegistroVacinacao registro = new RegistroVacinacao(
                        rst.getLong(1),
                        rst.getString(2),
                        rst.getLong(3),
                        rst.getDate(4),
                        rst.getInt(5)
                );
                registros.add(registro);
            }

            return registros;
        }
    }
}
