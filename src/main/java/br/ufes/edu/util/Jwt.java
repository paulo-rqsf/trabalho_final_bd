package br.ufes.edu.util;

import br.ufes.edu.Key;
import br.ufes.edu.models.Morador;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Jwt
{
    public static String jwtEncrypt(Morador morador) {
        return Jwts.builder()
                .setSubject(morador.getCpf())
                .setIssuer("localhost:8080")
                .setIssuedAt(new Date())
                .setExpiration(Date.from(LocalDateTime.now().plusMinutes(15L)
                        .atZone(ZoneId.systemDefault())
                        .toInstant()))
                .signWith(Key.KEY.getPrivate(), SignatureAlgorithm.RS512)
                .compact();
    }

    public static String jwtDecrypt(String token) {
        try {
            return Jwts.parserBuilder()
                    .setSigningKey(Key.KEY.getPrivate())
                    .build()
                    .parseClaimsJws(token)
                    .getBody()
                    .getSubject();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public static String getToken(HttpServletRequest request) {
        String cookieHeader = request.getHeader("Cookie");
        String token = null;

        if (cookieHeader != null) {
            String[] cookies = cookieHeader.split(";");
            for (String cookie : cookies) {
                if (cookie.contains("token")) {
                    token = cookie.split("=")[1].replace("\"", "").replace("Bearer ", "");
                }
            }
        }
        return token;
    }

    public static String getSubject(HttpServletRequest request) {
        try {
            String token = getToken(request);
            return jwtDecrypt(token);
        } catch (Exception e) {
            return null;
        }
    }
}
