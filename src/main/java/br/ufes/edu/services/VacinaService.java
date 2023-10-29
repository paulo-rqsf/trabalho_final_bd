package br.ufes.edu.services;

import br.ufes.edu.dao.VacinaDao;
import br.ufes.edu.factory.ConnectionFactory;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;

import java.beans.PropertyVetoException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

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

    public Response registrarVacina(){

        return Response.ok().build();
    }


//    public void makeOrderForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        List<Vaccine> vaccineList = vacDao.readAll();
//        Map<String, String> statesMap = States.returnStates();
//        for (Cookie cookie : request.getCookies()) {
//            if(cookie.getName().equals("user")) userId = cookie.getValue();
//        }
//        if(userId != null)  user = .readId(Long.parseLong(userId));
//
//        request.setAttribute("stateList", statesMap);
//        request.setAttribute("productList", vaccineList);
//        request.setAttribute("user", user);
//
//        request.getRequestDispatcher("/WEB-INF/view/makeOrder.jsp").forward(request, response);
//
//    }

    public void registrarVacinaForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/WEB-INF/view/registrarVacinas.jsp").forward(request, response);
    }


    public boolean isNumeric(String id){
        try {
            return Long.parseLong(id) > 0;
        }catch (Exception e){
            return false;
        }
    }
}
