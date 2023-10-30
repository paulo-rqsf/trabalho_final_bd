package br.ufes.edu.services;

import br.ufes.edu.dao.MoradorDao;
import br.ufes.edu.dao.RegistroVacinacaoDao;
import br.ufes.edu.dao.VacinaDao;
import br.ufes.edu.factory.ConnectionFactory;
import br.ufes.edu.models.Morador;
import br.ufes.edu.models.RegistroVacinacao;
import br.ufes.edu.models.Vacina;
import br.ufes.edu.util.Jwt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.SQLException;

public class RegistroVacinacaoService
{
    private final RegistroVacinacaoDao vacRecDao;
    private final MoradorDao morDao;
    private final VacinaDao vacDao;

    private static final String BOTAO_VOLTAR = "<div><a href=\"/redirectToArea\">Voltar</a></div>";

    public RegistroVacinacaoService() {
        try {
            Connection conn = new ConnectionFactory().getConnection();
            vacRecDao = new RegistroVacinacaoDao(conn);
            morDao = new MoradorDao(conn);
            vacDao = new VacinaDao(conn);
        } catch (SQLException | PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAdministrarVacinacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/administrarVacinacao.jsp").forward(request, response);
    }

    public void listarRegistros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String token = Jwt.getToken(request);

        if(token != null) {
            String cpf = Jwt.jwtDecrypt(token);
            request.setAttribute("registrosList", vacRecDao.lerRegistrosVacinacao(cpf));
            request.setAttribute("numeroSus", morDao.readUser(cpf).getNumeroSus());
        }
        request.getRequestDispatcher("/WEB-INF/view/registroVacinacao.jsp").forward(request, response);
    }

    public Response administrarVacinacao(RegistroVacinacao registroVacinacao) throws Exception {
        RegistroVacinacao registro = vacRecDao.readId(registroVacinacao.getIdRegistro());
        Vacina vacina = vacDao.readId(registroVacinacao.getIdVacina());

        try {
            if (registro != null) {
                if (!registro.getCpf().equals(registroVacinacao.getCpf())) {
                    return Response.status(Response.Status.UNAUTHORIZED).entity("CPF não corresponde ao registro"+BOTAO_VOLTAR).build();
                }
                if (registro.getDosesTomadas() >= vacina.getQuantidadeDoses()) {
                    return Response.status(Response.Status.UNAUTHORIZED).entity("Todas as doses desse registro já foram tomadas"+BOTAO_VOLTAR).build();
                }

                vacRecDao.update(registro.aumentaDose());
                return Response.status(Response.Status.OK).entity("Registro atualizado"+BOTAO_VOLTAR).build();
            }
            vacRecDao.save(registroVacinacao);

            return Response.status(Response.Status.CREATED).entity("Registro criado com sucesso"+BOTAO_VOLTAR).build();

        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

}
