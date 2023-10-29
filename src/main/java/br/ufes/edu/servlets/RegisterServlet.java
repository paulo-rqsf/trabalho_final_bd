package br.ufes.edu.servlets;

import br.ufes.edu.util.Lists;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;


@WebServlet(urlPatterns = {"/register"})
public class RegisterServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute("stateList", Lists.getStateList());
        request.setAttribute("sexList", Lists.getSexList());
        request.setAttribute("etniaList", Lists.getEtniaList());
        request.setAttribute("estadoCivilList", Lists.getEstadoCivil());
        request.setAttribute("escolaridadeList", Lists.getEscolaridadeList());


        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request,response);
    }

}
