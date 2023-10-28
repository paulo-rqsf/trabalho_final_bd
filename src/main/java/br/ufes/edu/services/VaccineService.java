package br.ufes.edu.services;

import br.ufes.edu.dao.VaccineDao;
import br.ufes.edu.factory.ConnectionFactory;
import br.ufes.edu.models.Address;
import br.ufes.edu.models.States;
import br.ufes.edu.models.User;
import br.ufes.edu.models.Vaccine;
import br.ufes.edu.util.Xml;
import br.ufes.edu.dao.UserDao;
import br.ufes.edu.models.ShowOrder;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.core.Response;
import org.w3c.dom.Element;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class VaccineService
{
    VaccineDao vacDao;

    public VaccineService() {
        try {
            Connection conn = ConnectionFactory.getConnection();
            vacDao = new VaccineDao(conn);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public Response makeVaccine(String name, String productIds, String token, String userId, Address address){
        Element rootElement = Xml.toXml(mail);
        String deliveryTime = Xml.getString("PrazoEntrega", rootElement);
        String feeValue = Xml.getString("Valor", rootElement);


        if(deliveryTime == null || feeValue == null) return Response.status(Response.Status.EXPECTATION_FAILED).entity("Cep inválido").build();


        return Response.status(Response.Status.OK).entity(new ShowOrder(order.getDeliveryTime(), order.getFeeValue(), order.getTotalValue())).build();
    }


    public void makeOrderForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Vaccine> vaccineList = vacDao.readAll();
        Map<String, String> statesMap = States.returnStates();
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("user")) userId = cookie.getValue();
        }
        if(userId != null)  user = .readId(Long.parseLong(userId));

        request.setAttribute("stateList", statesMap);
        request.setAttribute("productList", vaccineList);
        request.setAttribute("user", user);

        request.getRequestDispatcher("/WEB-INF/view/makeOrder.jsp").forward(request, response);

    }


    public boolean isNumeric(String id){
        try {
            return Long.parseLong(id) > 0;
        }catch (Exception e){
            return false;
        }
    }
}
