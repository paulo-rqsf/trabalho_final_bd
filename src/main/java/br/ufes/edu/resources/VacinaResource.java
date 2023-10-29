package br.ufes.edu.resources;

import br.ufes.edu.auth.Auth;
import br.ufes.edu.services.VacinaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/vacina")
public class VacinaResource {

    VacinaService vacinaService = new VacinaService();

    @Auth
    @GET
    @Path("/forward")
    public String makeOrderForward(@Context final HttpServletRequest request,
                                   @Context final HttpServletResponse response) throws Exception {
        vacinaService.registrarVacinaForward(request, response);
        return "";
    }

    @Auth
    @POST
    @Path("/registrar")
    @Produces(MediaType.APPLICATION_JSON)
    public Response registrarVacina(@FormParam("cpf") String cpf){

        return vacinaService.registrarVacina();
    }

}
