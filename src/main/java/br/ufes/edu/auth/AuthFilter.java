package br.ufes.edu.auth;

import br.ufes.edu.Key;
import io.jsonwebtoken.Jwts;
import jakarta.annotation.Priority;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.Provider;

import java.io.IOException;

import static br.ufes.edu.util.Jwt.jwtDecrypt;

@Provider
@Auth
@Priority(Priorities.AUTHENTICATION)
public class AuthFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext requestContext) throws IOException {
        try {
            String authHeader = requestContext.getCookies().get("token").getValue();
            String token = authHeader.substring("Bearer".length()).trim();
            jwtDecrypt(token);
        } catch (Exception e) {
            requestContext.abortWith(Response.status(Response.Status.UNAUTHORIZED).build());
        }

    }
}
