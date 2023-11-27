package br.ufes.edu.resources;

import br.ufes.edu.auth.Auth;
import br.ufes.edu.models.RegistroVacinacao;
import br.ufes.edu.services.RegistroVacinacaoService;
import br.ufes.edu.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.Response;

import java.util.Date;

@Path("/registros-vacinacao")
public class RegistroVacinacaoResource {

    RegistroVacinacaoService registroVacinacaoService = new RegistroVacinacaoService();

    @Auth
    @GET
    @Path("/forward")
    public String getForm(@Context final HttpServletRequest request,
                                       @Context final HttpServletResponse response) throws Exception {
        registroVacinacaoService.getAdministrarVacinacao(request, response);
        return "";
    }
    @Auth
    @POST
    @Path("/administrar")
    public Response administrarVacinacao(@FormParam("idRegistro") String idRegistro,
                                         @FormParam("cpf") String cpf,
                                         @FormParam("idVacina") String idVacina) throws Exception {

        return registroVacinacaoService.administrarVacinacao(new RegistroVacinacao(
                Long.parseLong(idRegistro),
                cpf,
                Long.parseLong(idVacina.split(" - ")[0].trim()),
                DateUtil.transformaData(new Date()),
                1));
    }

    @Auth
    @GET
    @Path("/list")
    public String listarRegistros(@Context final HttpServletRequest request,
                                  @Context final HttpServletResponse response) throws Exception {
        registroVacinacaoService.listarRegistros(request, response);
        return "";
    }


}
