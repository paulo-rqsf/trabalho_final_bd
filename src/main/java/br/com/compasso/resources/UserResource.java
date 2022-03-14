package br.com.compasso.resources;

import br.com.compasso.models.User;
import br.com.compasso.services.UserService;
import jakarta.ws.rs.*;
import br.com.compasso.auth.Auth;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;

@Path("/user")
public class UserResource {
    UserService userService = new UserService();

    @Path("login")
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(@FormParam("username") String username,
                          @FormParam("password") String password) throws IOException {
        return userService.login(username, password);
    }

    @POST
    @Path("register")
    @Produces(MediaType.APPLICATION_JSON)
    public Response register(@FormParam("username") String username,
                             @FormParam("password") String password,
                             @FormParam("name") String name){
        return userService.register(new User(username, password, name));
    }

    @Auth
    @GET
    @Path("list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers(){
        return userService.listAllUsers();
    }

    @Auth
    @GET
    @Path("blabla")
    @Produces("text/plain")
    public String Hello(){ return "Oiiiiiiiii!"; }

}
