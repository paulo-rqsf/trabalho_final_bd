package br.ufes.edu.dao;


import br.ufes.edu.models.Address;
import br.ufes.edu.models.User;
import br.ufes.edu.models.User;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class UserDao
{
    private Connection connection;

    public UserDao(Connection connection){
        this.connection = connection;
    }

    public List<User> readAll(){
        String sql = "SELECT * FROM Users";
        List<User> users = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            return fillList(users, pstm);

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

//    public List<User> readLess(){
//        String sql = "SELECT NOME, CPF FROM USERS";
//        List<User> users = new ArrayList<>();
//
//        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
//
//            pstm.execute();
//            return fillList(users, pstm);
//
//        } catch(SQLException e) {
//            throw new RuntimeException(e);
//        }
//    }

    public User readUser(String cpf){
        String sql = "SELECT * FROM USERS WHERE cpf = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, cpf);
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                if (rst.next()) {
                    return getUser(rst);
                }

                return null;
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void save(User user){
        String sql = "INSERT INTO USER (" +
                "NOME," +
                " CPF," +
                " NUMERO_SUS," +
                " NOME_SOCIAL," +
                " DATA_NASCIMENTO," +
                " SEXO," +
                " NOME_MAE," +
                " TELEFONE," +
                " EMAIL, CEP," +
                " LOGRADOURO," +
                " NUMERO," +
                " COMPLEMENTO," +
                " BAIRRO," +
                " CIDADE," +
                " UF) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {
            Address address = user.getAddress();

            pstm.setString(1, user.getNome());
            pstm.setString(2, user.getCpf());
            pstm.setString(3, user.getNumeroSus());
            pstm.setString(4, user.getNomeSocial());
            pstm.setString(5, user.getDataNascimento());
            pstm.setString(6, user.getSexo());
            pstm.setString(7, user.getNomeMae());
            pstm.setString(8, user.getTelefone());
            pstm.setString(9, user.getEmail());
            pstm.setString(10, user.getSenha());
            pstm.setString(11, address.getCep());
            pstm.setString(12, address.getLogradouro());
            pstm.setString(13, address.getBairro());
            pstm.setString(14, address.getCidade());
            pstm.setString(15, address.getComplemento());
            pstm.setString(16, address.getUf());
            pstm.setInt(17, address.getNumero());

            pstm.execute();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<User> fillList(List<User> users, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                User user = getUser(rst);

                users.add(user);
            }

            return users;
        }
    }

    private User getUser(ResultSet rst) throws SQLException {
        Address add = new Address(rst.getString(11), rst.getString(12), rst.getString(13), rst.getString(14), rst.getString(15), rst.getString(16), rst.getInt(17));
        return new User(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getString(7), rst.getString(8), rst.getString(9), rst.getString(10), add);
    }
}
