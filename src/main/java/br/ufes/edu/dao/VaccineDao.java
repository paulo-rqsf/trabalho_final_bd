package br.ufes.edu.dao;


import br.ufes.edu.models.Vaccine;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VaccineDao
{
    private Connection connection;

    public VaccineDao(Connection connection){
        this.connection = connection;
    }

    public List<Vaccine> readAll() {
        String sql = "SELECT * FROM Vaccines";
        List<Vaccine> vaccines = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            return fillList(vaccines, pstm);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Vaccine> readId(Long id){
        String sql = "SELECT * FROM VACCINES WHERE vaccine_id = ?";
        List<Vaccine> vaccines = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setLong(1, id);
            pstm.execute();

            return fillList(vaccines, pstm);
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    private List<Vaccine> fillList(List<Vaccine> vaccines, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Vaccine vaccine = new Vaccine(rst.getLong(1), rst.getString(2), rst.getString(3), rst.getString(4));
                vaccines.add(vaccine);
            }

            return vaccines;
        }
    }
}
