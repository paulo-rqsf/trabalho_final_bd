package br.ufes.edu.resources;

import br.ufes.edu.auth.Auth;
import br.ufes.edu.models.Vacina;
import br.ufes.edu.services.VacinaService;
import br.ufes.edu.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

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
    public Response registrarVacina(@FormParam("idVacina") String idVacina,
                                    @FormParam("nome") String nome,
                                    @FormParam("descricao") String descricao,
                                    @FormParam("lote") String lote,
                                    @FormParam("fabricante") String fabricante,
                                    @FormParam("dataValidade") String dataValidade,
                                    @FormParam("quantidadeDoses") String quantidadeDoses,
                                    @FormParam("intervaloDoses") String intervaloDoses) throws Exception {

        return vacinaService.registrarVacina(new Vacina(
                Long.parseLong(idVacina),
                nome,
                descricao,
                lote,
                fabricante,
                DateUtil.transformaDataString(dataValidade),
                Integer.parseInt(quantidadeDoses),
                Integer.parseInt(intervaloDoses)));
    }

    @Auth
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Vacina> list() throws Exception {
        return vacinaService.listarVacinas();
    }

}
