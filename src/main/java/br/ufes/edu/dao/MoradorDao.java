package br.ufes.edu.dao;


import br.ufes.edu.models.Endereco;
import br.ufes.edu.models.Morador;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MoradorDao
{
    private final Connection connection;

    public MoradorDao(Connection connection){
        this.connection = connection;
    }

    public List<Morador> readAll(){
        String sql = "SELECT * FROM Morador";
        List<Morador> moradores = new ArrayList<>();

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.execute();

            return fillList(moradores, pstm);

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

    public Morador readUser(String cpf){
        String sql = "SELECT * FROM Morador WHERE cpf = ?";

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

    public String getNumSus(String cpf) {
        String sql = "SELECT NUMERO_SUS FROM Morador WHERE cpf = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, cpf);
            pstm.execute();

            try (ResultSet rst = pstm.getResultSet()) {
                if (rst.next()) {
                    return rst.getString(1);
                }
                return null;
            }
        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Morador readUserCpfOrEmail(String username) {
        String sql = "SELECT * FROM Morador WHERE cpf = ? OR email = ?";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            pstm.setString(1, username);
            pstm.setString(2, username);
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

    public void save(Morador morador){
        String sql = "INSERT INTO Morador (" +
                "NOME," +
                "EMAIL, " +
                "SENHA, " +
                "CPF, " +
                "NUMERO_SUS, " +
                "NOME_SOCIAL, " +
                "DATA_NASCIMENTO, " +
                "SEXO, " +
                "NOME_MAE, " +
                "TELEFONE, " +
                "CEP, " +
                "LOGRADOURO, " +
                "NUMERO, " +
                "BAIRRO, " +
                "CIDADE, " +
                "UF, " +
                "COMPLEMENTO, " +
                "ESTADO_CIVIL, " +
                "ESCOLARIDADE, " +
                "ETNIA, " +
                "TEM_PLANO_SAUDE) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement pstm = connection.prepareStatement(sql)) {
            Endereco endereco = morador.getEndereco();

            pstm.setString(1, morador.getNome());
            pstm.setString(2, morador.getEmail());
            pstm.setString(3, morador.getSenha());
            pstm.setString(4, morador.getCpf());
            pstm.setString(5, morador.getNumeroSus());
            pstm.setString(6, morador.getNomeSocial());
            pstm.setDate(7, morador.getDataNascimentoSql());
            pstm.setString(8, morador.getSexo());
            pstm.setString(9, morador.getNomeMae());
            pstm.setString(10, morador.getTelefone());
            pstm.setString(11, endereco.getCep());
            pstm.setString(12, endereco.getLogradouro());
            pstm.setInt(13, endereco.getNumero());
            pstm.setString(14, endereco.getBairro());
            pstm.setString(15, endereco.getCidade());
            pstm.setString(16, endereco.getUf());
            pstm.setString(17, endereco.getComplemento());
            pstm.setString(18, morador.getEstadoCivil());
            pstm.setString(19, morador.getEscolaridade());
            pstm.setString(20, morador.getEtnia());
            pstm.setBoolean(21, morador.isTemPlanoSaude());

            pstm.execute();

        } catch(SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    private List<Morador> fillList(List<Morador> moradores, PreparedStatement pstm) throws SQLException {
        try (ResultSet rst = pstm.getResultSet()) {
            while (rst.next()) {
                Morador morador = getUser(rst);

                moradores.add(morador);
            }

            return moradores;
        }
    }

    private Morador getUser(ResultSet rst) throws SQLException {
        Endereco add = new Endereco(rst.getString(11), rst.getString(12), rst.getInt(13), rst.getString(14), rst.getString(15), rst.getString(16), rst.getString(17));
        return new Morador(rst.getString(1), rst.getString(2), rst.getString(3), rst.getString(4), rst.getString(5), rst.getString(6), rst.getDate(7), rst.getString(8), rst.getString(9), rst.getString(10), rst.getString(18), rst.getString(19), rst.getString(20), rst.getBoolean(21), rst.getBoolean(22), add);
    }

    public void update(Morador morador) {
        String sql = "UPDATE Morador SET ADMIN = true WHERE CPF = ?";
        try (PreparedStatement pstm = connection.prepareStatement(sql)) {

            pstm.setString(1, morador.getCpf());
            pstm.execute();

        } catch(SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
