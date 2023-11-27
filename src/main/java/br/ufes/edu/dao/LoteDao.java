package br.ufes.edu.dao;


import br.ufes.edu.models.Lote;
import br.ufes.edu.util.DateUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class LoteDao
{
    private Connection connection;

    public LoteDao(Connection connection){
        this.connection = connection;
    }

    public List<Lote> readAll() {
        String sql = "SELECT * FROM Lotes";
        List<Lote> lotes = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            return fillList(lotes, pstm);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Lote readId(Long id){
        String sql = "SELECT * FROM Lotes WHERE CODIGO_LOTE = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.execute();

            try(ResultSet rst = pstm.getResultSet()) {
                rst.next();
                return new Lote(
                        rst.getLong(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getDate(5)
                );
            }
        } catch(SQLException e) {
            return null;
        }
    }

    public void save(Lote lote) throws SQLException {
        String sql = "INSERT INTO Lotes (CODIGO_LOTE, CNPJ_FABRICANTE, NOME_FABRICANTE, QUANTIDADE_VACINAS, DATA_FABRICACAO) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, lote.getCodigoLote());
            pstm.setString(2, lote.getCnpjFabricante());
            pstm.setString(3, lote.getNomeFabricante());
            pstm.setInt(4, lote.getQuantidadeVacinas());
            pstm.setDate(5, DateUtil.getDataSql(lote.getDataFabricacao()));

            pstm.execute();
        }
    }

    private List<Lote> fillList(List<Lote> lotes, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Lote lote = new Lote(
                        rst.getLong(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getInt(4),
                        rst.getDate(5)
                );
                lotes.add(lote);
            }

            return lotes;
        }
    }
}
