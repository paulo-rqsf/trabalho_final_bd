package br.ufes.edu.resources;

import br.ufes.edu.auth.Auth;
import br.ufes.edu.services.VacinaService;
import jakarta.ws.rs.FormParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/vaccine")
public class VacinaResource {

    VacinaService vacinaService = new VacinaService();

//    @Auth
//    @GET
//    @Path("/viewAll")
//    public String makeOrderForward(@Context final HttpServletRequest request,
//                                   @Context final HttpServletResponse response) throws Exception {
//        vaccineService.makeOrderForward(request, response);
//        return "";
//    }

    @Auth
    @POST
    @Path("/takeVaccine")
    @Produces(MediaType.APPLICATION_JSON)
    public Response takeVaccine(@FormParam("cpf") String cpf){

        return vacinaService.takeVaccine();
    }

}
