package br.ufes.edu.resources;

import br.ufes.edu.auth.Auth;
import br.ufes.edu.models.Endereco;
import br.ufes.edu.models.Morador;
import br.ufes.edu.services.MoradorService;
import br.ufes.edu.util.DateUtil;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.text.ParseException;

@Path("/user")
public class MoradorResource {
    MoradorService moradorService = new MoradorService();

    @Path("/login")
    @POST
    public Response login(@FormParam("username") String username,
                          @FormParam("senha") String senha) throws IOException {
        return moradorService.login(username, senha);
    }

    @POST
    @Path("/register")
    public Response register(@FormParam("nome") String nome,
                             @FormParam("email") String email,
                             @FormParam("senha") String senha,
                             @FormParam("cpf") String cpf,
                             @FormParam("numeroSus") String numeroSus,
                             @FormParam("nomeSocial") String nomeSocial,
                             @FormParam("dataNascimento") String dataNascimento,
                             @FormParam("sexo") String sexo,
                             @FormParam("nomeMae") String nomeMae,
                             @FormParam("telefone") String telefone,
                             @FormParam("cep") String cep,
                             @FormParam("logradouro") String logradouro,
                             @FormParam("bairro") String bairro,
                             @FormParam("cidade") String cidade,
                             @FormParam("complemento") String complemento,
                             @FormParam("uf") String uf,
                             @FormParam("numero") int numero,
                             @FormParam("estadoCivil") String estadoCivil,
                             @FormParam("escolaridade") String escolaridade,
                             @FormParam("etnia") String etnia,
                             @FormParam("temPlano") String temPlano) throws ParseException {

        return moradorService.register(
                new Morador(
                        nome,
                        email,
                        senha,
                        cpf,
                        numeroSus,
                        nomeSocial,
                        DateUtil.transformaDataString(dataNascimento),
                        moradorService.transformaSexo(sexo),
                        nomeMae,
                        telefone,
                        estadoCivil,
                        escolaridade,
                        etnia,
                        moradorService.transformaTemPlano(temPlano),
                        false,
                        new Endereco(cep, logradouro, numero, bairro, cidade, uf, complemento)));
    }

    @Auth
    @Path("/loggout")
    @GET
    public Response loggout() {
        return moradorService.loggout();
    }

    @Auth
    @Path("/admin")
    @POST
    public Response admin(@FormParam("cpf") String cpf) throws IOException {
        return moradorService.admin(cpf);
    }

    @Auth
    @GET
    @Path("/list")
    @Produces(MediaType.APPLICATION_JSON)
    public Response listUsers(){
        return moradorService.listAllUsers();
    }


}
