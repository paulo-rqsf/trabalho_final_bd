package br.ufes.edu.servlets;

import br.ufes.edu.models.UtilLists;
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

        request.setAttribute("stateList", UtilLists.getStateList());
        request.setAttribute("sexList", UtilLists.getSexList());
        request.setAttribute("etniaList", UtilLists.getEtniaList());
        request.setAttribute("estadoCivilList", UtilLists.getEstadoCivil());
        request.setAttribute("escolaridadeList", UtilLists.getEscolaridadeList());


        request.getRequestDispatcher("/WEB-INF/view/register.jsp").forward(request,response);
    }

}
