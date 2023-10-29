package br.ufes.edu.dao;


import br.ufes.edu.models.Vacina;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VacinaDao
{
    private Connection connection;

    public VacinaDao(Connection connection){
        this.connection = connection;
    }

    public List<Vacina> readAll() {
        String sql = "SELECT * FROM Vacinas";
        List<Vacina> vacinas = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            return fillList(vacinas, pstm);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Vacina readId(Long id){
        String sql = "SELECT * FROM Vacinas WHERE ID_VACINA = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()) {
                rst.next();
                return new Vacina(
                        rst.getLong(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getDate(6),
                        rst.getInt(7),
                        rst.getInt(8)
                );
            }
        } catch(SQLException e) {
            return null;
        }
    }

    public void save(Vacina vacina) throws SQLException {
        String sql = "INSERT INTO Vacinas (ID_VACINA, NOME, DESCRICAO, LOTE, FABRICANTE, DATA_VALIDADE, QUANTIDADE_DOSES, INTERVALO_DOSES) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, vacina.getIdVacina());
            pstm.setString(2, vacina.getNome());
            pstm.setString(3, vacina.getDescricao());
            pstm.setString(4, vacina.getLote());
            pstm.setString(5, vacina.getFabricante());
            pstm.setDate(6, new java.sql.Date(vacina.getDataValidade().getTime()));
            pstm.setInt(7, vacina.getQuantidadeDoses());
            pstm.setInt(8, vacina.getIntervaloDoses());

            pstm.execute();
        }
    }

    private List<Vacina> fillList(List<Vacina> vacinas, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Vacina vacina = new Vacina(
                        rst.getLong(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getDate(6),
                        rst.getInt(7),
                        rst.getInt(8)
                );
                vacinas.add(vacina);
            }

            return vacinas;
        }
    }
}
