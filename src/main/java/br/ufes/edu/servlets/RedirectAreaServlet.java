package br.ufes.edu.servlets;

import br.ufes.edu.models.Morador;
import br.ufes.edu.services.MoradorService;
import br.ufes.edu.util.Jwt;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.Optional;


@WebServlet(urlPatterns = {"/redirectToArea"})
public class RedirectAreaServlet extends HttpServlet {

    MoradorService moradorService;

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        moradorService = new MoradorService();
        String subject = Jwt.getSubject(request);
        if (subject == null) {
            response.sendRedirect("/login");
            return;
        }
        Morador morador = moradorService.getMorador(subject);
        try {
            if (morador != null) {
                if (morador.isAdmin()) {
                    response.sendRedirect("/redirect?forward=areaAdmin.jsp");
                } else {
                    response.sendRedirect("/redirect?forward=areaMorador.jsp");
                }

            } else {
                response.sendRedirect("/login");
            }

        } catch (Exception e) {
            response.getWriter().write(e.getMessage());
        }
    }

}
