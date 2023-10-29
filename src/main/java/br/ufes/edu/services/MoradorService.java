package br.ufes.edu.services;

import br.ufes.edu.Key;
import br.ufes.edu.dao.MoradorDao;
import br.ufes.edu.factory.ConnectionFactory;
import br.ufes.edu.models.Morador;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.ws.rs.core.NewCookie;
import jakarta.ws.rs.core.Response;

import java.net.URI;
import java.sql.Connection;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MoradorService {

    private final MoradorDao dao;

    public MoradorService() {
        try {
            Connection conn = new ConnectionFactory().getConnection();
            this.dao = new MoradorDao(conn);

        } catch (Exception e) {
            throw new RuntimeException("Erro ao conectar com o banco de dados: " + e.getMessage());
        }
    }

    public Response login(String username, String senha) {
        try{
            Morador morador = dao.readUserCpfOrEmail(username);
            if(morador != null && morador.getSenha().equals(senha))
            {
                String jwtToken = Jwts.builder()
                        .setSubject(morador.getCpf())
                        .setIssuer("localhost:8080")
                        .setIssuedAt(new Date())
                        .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L)
                                .atZone(ZoneId.systemDefault())
                                .toInstant()))
                        .signWith(Key.KEY.getPrivate(), SignatureAlgorithm.RS512)
                        .compact();

                NewCookie cookie = new NewCookie("token", "Bearer " + jwtToken, "/", "localhost", "token", 60*60, false, true);

                return Response.seeOther(URI.create("http://localhost:8080/redirect?forward=tomarVacina.jsp")).cookie(cookie).entity(jwtToken).build();
            }
            return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario e/ou senha Invalidos!").build();
        }
        catch(Exception ex)
        {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(ex.getMessage()).build();
        }
    }

    public Response register(Morador morador) {
        Morador u = dao.readUser(morador.getCpf());

        if(u != null){
            return Response.status(Response.Status.FOUND).header("Location","http://localhost:8080/redirect?forward=errorRegister.jsp").build();

        }
        try {
            dao.save(morador);
            return Response.status(Response.Status.FOUND).header("Location","http://localhost:8080/login").build();

        } catch (Exception e) {
            System.out.println(Arrays.toString(e.getStackTrace()));
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response listAllUsers() {
        List<Morador> listMoradors = dao.readAll();
        return Response.status(Response.Status.OK).entity(listMoradors).build();
    }

    public boolean transformaTemPlano(String temPlano) {
        return temPlano.equals("Sim");
    }

    public String transformaSexo(String sexo) {
        if(sexo.equals("Masculino"))
            return "M";
        else if(sexo.equals("Feminino"))
            return "F";
        else
            return "O";
    }
}
