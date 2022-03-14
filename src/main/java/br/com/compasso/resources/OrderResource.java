package br.com.compasso.resources;

import br.com.compasso.auth.Auth;
import br.com.compasso.models.Address;
import br.com.compasso.services.OrderService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/order")
public class OrderResource {

    OrderService orderService = new OrderService();

    @Auth
    @GET
    @Path("/cart")
    public String makeOrderForward(@Context final HttpServletRequest request,
                                   @Context final HttpServletResponse response) throws Exception {
        orderService.makeOrderForward(request, response);
        return "";
    }

    @Auth
    @POST
    @Path("makeOrder/{productIds}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response makeOrder(@FormParam("name") String name,
                              @FormParam("street") String street,
                              @FormParam("destinyCep") String destinyCep,
                              @FormParam("district") String district,
                              @FormParam("city") String city,
                              @FormParam("state") String state,
                              @PathParam("productIds") String productIds,
                              @CookieParam("token") String token,
                              @CookieParam("user") String userId){

        return orderService.makeOrder(name, productIds,token, userId, new Address(street, district, city, destinyCep, state));
    }

}
