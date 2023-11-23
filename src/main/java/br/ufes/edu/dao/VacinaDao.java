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
                        rst.getLong(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getDate(5),
                        rst.getInt(7),
                        rst.getInt(8)
                );
            }
        } catch(SQLException e) {
            return null;
        }
    }

    public void save(Vacina vacina) throws SQLException {
        String sql = "INSERT INTO Vacinas (ID_VACINA, CODIGO_LOTE ,NOME, DESCRICAO, DATA_VALIDADE, QUANTIDADE_DOSES, INTERVALO_DOSES) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, vacina.getIdVacina());
            pstm.setLong(2, vacina.getCodigoLote());
            pstm.setString(3, vacina.getNome());
            pstm.setString(4, vacina.getDescricao());
            pstm.setDate(5, new java.sql.Date(vacina.getDataValidade().getTime()));
            pstm.setInt(6, vacina.getQuantidadeDoses());
            pstm.setInt(7, vacina.getIntervaloDoses());

            pstm.execute();
        }
    }

    private List<Vacina> fillList(List<Vacina> vacinas, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Vacina vacina = new Vacina(
                        rst.getLong(1),
                        rst.getLong(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getDate(5),
                        rst.getInt(6),
                        rst.getInt(7)
                );
                vacinas.add(vacina);
            }

            return vacinas;
        }
    }
}
