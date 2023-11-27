package br.ufes.edu.resources;

import br.ufes.edu.auth.Auth;
import br.ufes.edu.models.Lote;
import br.ufes.edu.services.LoteService;
import br.ufes.edu.util.DateUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.sql.Date;
import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalTime;
import java.util.List;

@Path("/lote")
public class LoteResource {

    LoteService loteService = new LoteService();

    @Auth
    @GET
    @Path("/forward")
    public String makeOrderForward(@Context final HttpServletRequest request,
                                   @Context final HttpServletResponse response) throws Exception {
        loteService.registrarLoteForward(request, response);
        return "";
    }


    @Auth
    @POST
    @Path("/registrar")
    public Response registrarLote(@FormParam("codigoLote") String codigoLote,
                                  @FormParam("cnpjFabricante") String cnpjFabricante,
                                  @FormParam("nomeFabricante") String nomeFabricante) throws Exception {

        return loteService.registrarLote(new Lote(
                Long.parseLong(codigoLote),
                cnpjFabricante, nomeFabricante,
                0,
                Date.from(Instant.now())));
    }


    @Auth
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Lote> list() throws Exception {
        return loteService.listarLotes();
    }

}
