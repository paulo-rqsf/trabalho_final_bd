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
import java.util.List;

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

        Vacina vacinaExistente = vacDao.readId(vacina.getIdVacina());
        try {
            if (vacinaExistente != null) {
                throw new Exception("Vacina já cadastrada!");
            }
            vacDao.save(vacina);
            return Response.seeOther(URI.create("http://localhost:8080/redirect?forward=areaAdmin.jsp")).build();

        } catch (Exception e) {
            return Response.seeOther(URI.create("http://localhost:8080/redirect?forward=errorRegistroVacina.jsp")).build();
        }
    }

    public List<Vacina> listarVacinas() throws Exception {
        return vacDao.readAll();
    }

    public void registrarVacinaForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/registrarVacina.jsp").forward(request, response);
    }

}
