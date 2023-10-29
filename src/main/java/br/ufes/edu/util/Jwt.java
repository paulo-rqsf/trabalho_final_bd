package br.ufes.edu.util;

import br.ufes.edu.Key;
import br.ufes.edu.models.Morador;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

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
        return Jwts.parserBuilder()
                .setSigningKey(Key.KEY.getPrivate())
                .build()
                .parseClaimsJws(token)
                .getBody()
                .getSubject();
    }
}
