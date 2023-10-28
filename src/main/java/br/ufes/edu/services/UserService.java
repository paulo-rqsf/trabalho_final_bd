package br.ufes.edu.services;

import br.ufes.edu.Key;
import br.ufes.edu.dao.UserDao;
import br.ufes.edu.factory.ConnectionFactory;
import br.ufes.edu.models.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;
import java.util.List;

public class UserService {

    private final UserDao dao;

    public UserService() {
        try {
            Connection conn = new ConnectionFactory().getConnection();
            this.dao = new UserDao(conn);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    public Response login(String cpf, String senha) {
        try{
            User user = dao.readUser(cpf);
            if(user != null && (user.getCpf().equals(cpf) && user.getSenha().equals(senha)))
            {
                String jwtToken = Jwts.builder()
                        .setSubject(cpf)
                        .setIssuer("localhost:8080")
                        .setIssuedAt(new Date())
                        .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()))
                        .signWith(Key.KEY.getPrivate(), SignatureAlgorithm.RS512)
                        .compact();

                NewCookie cookie = new NewCookie("token", "Bearer " + jwtToken, "/", "localhost", "token", 60*60, false, true);

                return Response.seeOther(URI.create("http://localhost:8080/api/order/cart")).cookie(cookie).entity(jwtToken).build();
            }
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario e/ou senha Invalidos!").build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    public Response register(User user) {
        User u = dao.readUser(user.getCpf());

        if(u != null){
            return Response.status(Response.Status.BAD_REQUEST).entity("Usuário já existe no sistema!").build();
        }

        dao.save(user);
        return Response.seeOther(URI.create("http://localhost:8080/login")).entity("Usuario cadastrado com sucesso!").build();
    }

    public Response listAllUsers() {
        List<User> listUsers = dao.readAll();
        return Response.status(Response.Status.OK).entity(listUsers).build();
    }
}
