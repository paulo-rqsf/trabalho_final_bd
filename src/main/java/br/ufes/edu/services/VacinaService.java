package br.ufes.edu.services;

import br.ufes.edu.dao.VacinaDao;
import br.ufes.edu.factory.ConnectionFactory;
import br.ufes.edu.models.Vacina;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;

public class VacinaService
{
    VacinaDao vacDao;

    public VacinaService() {
        try {
            Connection conn = new ConnectionFactory().getConnection();
            vacDao = new VacinaDao(conn);
        } catch (SQLException | PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    public Response registrarVacina(Vacina vacina) throws Exception {
        return Response.seeOther(URI.create("https://www.google.com")).build();
    }

    public void registrarVacinaForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/registrarVacina.jsp").forward(request, response);
    }

    public void getRegistroVacinacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/registroVacinacao.jsp").forward(request, response);
    }

    public boolean isNumeric(String id){
        try {
            return Long.parseLong(id) > 0;
        }catch (Exception e){
            return false;
        }
    }
}
