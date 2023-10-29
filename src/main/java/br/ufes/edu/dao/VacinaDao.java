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
        String sql = "SELECT * FROM Vaccines";
        List<Vacina> vacinas = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            return fillList(vacinas, pstm);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vacina> readId(Long id){
        String sql = "SELECT * FROM VACCINES WHERE vaccine_id = ?";
        List<Vacina> vacinas = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.execute();

            return fillList(vacinas, pstm);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Vacina> fillList(List<Vacina> vacinas, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                // Create for me a new Vaccine
                Vacina vacina = new Vacina(
                        rst.getLong(1),
                        rst.getString(2),
                        rst.getString(3),
                        rst.getString(4),
                        rst.getString(5),
                        rst.getString(6),
                        rst.getInt(7),
                        rst.getInt(8),
                        rst.getInt(9)
                );

                // Add this Vaccine to my list
                vacinas.add(vacina);
            }

            return vacinas;
        }
    }
}
