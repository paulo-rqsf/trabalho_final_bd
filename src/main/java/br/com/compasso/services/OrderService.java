package br.com.compasso.services;

import br.com.compasso.dao.*;
import br.com.compasso.models.*;
import br.com.compasso.util.Xml;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.Response;
import org.w3c.dom.Element;

import java.io.IOException;
import java.util.List;

public class OrderService
{
    ProductDao productDao = new ProductDao();
    OrderDao orderDao = new OrderDao();

    public Response makeOrder(String name, String productIds, String token, String userId, Address address){
        String mail = calculateFee("29931640", address.getCep(), "04510");

        Element rootElement = Xml.toXml(mail);
        String deliveryTime = Xml.getString("PrazoEntrega", rootElement);
        String feeValue = Xml.getString("Valor", rootElement);


        if(deliveryTime == null || feeValue == null) return Response.status(Response.Status.EXPECTATION_FAILED).entity("Cep inválido").build();

        feeValue = feeValue.replaceAll(",", ".");

        Order order = new Order(Double.parseDouble(feeValue), Integer.parseInt(deliveryTime));
        makeOrderProduct(name, productIds, userId, order, address);

        return Response.status(Response.Status.OK).entity(new ShowOrder(order.getDeliveryTime(), order.getFeeValue(), order.getTotalValue())).build();
    }


    public void makeOrderProduct(String name, String productIds, String userId, Order order, Address address) {
        String[] ids = productIds.split("-");
        for (String Strid : ids) {
            String[] idsAndQts = Strid.split(":");
            if(isNumeric(idsAndQts[0]) && isNumeric(idsAndQts[1])){
                Product product = productDao.readId(Long.parseLong(idsAndQts[0]));
                if(product != null) order.addProductOrder(new OrderProduct(Integer.parseInt(idsAndQts[1]), order, product));
            }
        }
        User user = updateUser(name, userId, address);

        order.setTotalValue(order.getFeeValue() + getTotalOrderProduct(order.getOrderProductList()));
        order.setUser(user);
        orderDao.save(order);
    }

    private User updateUser(String name, String userId, Address address) {
        UserDao userDao = new UserDao();
        User user = userDao.readId(Long.parseLong(userId));
        new AddressDao().save(address);
        user.setAddress(address);
        user.setName(name);

        userDao.update(user);
        return user;
    }

    private String calculateFee(String origin, String destiny, String service) {
        Client client = ClientBuilder.newClient();
        WebTarget target = client.target("http://ws.correios.com.br");
        String content = target.path("/calculador/CalcPrecoPrazo.aspx")
                .queryParam("nCdEmpresa", "")
                .queryParam("sDsSenha", "")
                .queryParam("sCepOrigem", origin)
                .queryParam("sCepDestino", destiny)
                .queryParam("nVlPeso", "1")
                .queryParam("nCdFormato", "3")
                .queryParam("nVlComprimento", "10")
                .queryParam("nVlAltura", "0")
                .queryParam("nVlLargura", "10")
                .queryParam("sCdMaoPropria", "n")
                .queryParam("nVlValorDeclarado", "0")
                .queryParam("sCdAvisoRecebimento", "s")
                .queryParam("nCdServico", service)
                .queryParam("nVlDiametro", "0")
                .queryParam("StrRetorno", "xml")
                .queryParam("nIndicaCalculo", "3")
                .request().get(String.class);
        return content;
    }

    public void makeOrderForward(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Product> productList = new ProductDao().readAll();
        List<States> statesList = new StatesDao().readAll();
        String userId = null;
        User user = null;
        for (Cookie cookie : request.getCookies()) {
            if(cookie.getName().equals("user")) userId = cookie.getValue();
        }
        if(userId != null)  user = new UserDao().readId(Long.parseLong(userId));

        request.setAttribute("stateList", statesList);
        request.setAttribute("productList", productList);
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
    public double getTotalOrderProduct(List<OrderProduct> listOP){
        double total = 0;
        for (OrderProduct op : listOP) {
            total += op.getQt()*op.getUnitPrice();
        }
        return total;
    }
}
