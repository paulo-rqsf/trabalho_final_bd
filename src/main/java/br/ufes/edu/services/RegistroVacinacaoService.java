package br.ufes.edu.services;

import br.ufes.edu.dao.MoradorDao;
import br.ufes.edu.dao.RegistroVacinacaoDao;
import br.ufes.edu.dao.VacinaDao;
import br.ufes.edu.factory.ConnectionFactory;
import br.ufes.edu.models.Morador;
import br.ufes.edu.util.Jwt;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class RegistroVacinacaoService
{
    RegistroVacinacaoDao vacRecDao;
    MoradorDao morDao;

    public RegistroVacinacaoService() {
        try {
            Connection conn = new ConnectionFactory().getConnection();
            vacRecDao = new RegistroVacinacaoDao(conn);
            morDao = new MoradorDao(conn);
        } catch (SQLException | PropertyVetoException e) {
            throw new RuntimeException(e);
        }
    }

    public void getAdministrarVacinacao(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.getRequestDispatcher("/WEB-INF/view/administrarVacinacao.jsp").forward(request, response);
    }

    public void listarRegistros(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        Cookie[] cookies = request.getCookies();
        String token = null;
        for (Cookie cookie : cookies) {
            if(cookie.getName().equals("token")) token = cookie.getValue();
        }
        if(token != null) {
            String cpf = Jwt.jwtDecrypt(token);
            String numSus = morDao.getNumSus(cpf);
            request.setAttribute("registrosList", vacRecDao.lerRegistrosVacinacao(numSus));
        }
        request.getRequestDispatcher("/WEB-INF/view/registroVacinacao.jsp").forward(request, response);
    }

}
