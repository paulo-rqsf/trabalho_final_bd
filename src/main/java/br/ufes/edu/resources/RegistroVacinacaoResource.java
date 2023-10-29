package br.ufes.edu.resources;

import br.ufes.edu.auth.Auth;
import br.ufes.edu.services.RegistroVacinacaoService;
import br.ufes.edu.services.VacinaService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/registros-vacinacao")
public class RegistroVacinacaoResource {

    RegistroVacinacaoService registroVacinacaoService = new RegistroVacinacaoService();

    @Auth
    @GET
    @Path("/forward")
    public String makeOrderForward(@Context final HttpServletRequest request,
                                   @Context final HttpServletResponse response) throws Exception {
        registroVacinacaoService.getRegistroVacinacao(request, response);
        return "";
    }


}
