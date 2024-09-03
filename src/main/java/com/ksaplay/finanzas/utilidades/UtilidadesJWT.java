package com.ksaplay.finanzas.utilidades;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.auth0.jwt.interfaces.JWTVerifier;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

public class UtilidadesJWT {

    // Clave secreta para firmar los JWT
    private static final String CLAVE_SECRETA = "mi_clave_secreta_super_segura"; // Cambiar a una clave secreta robusta
    private static final Algorithm ALGORITMO = Algorithm.HMAC256(CLAVE_SECRETA);

    // Genera un token JWT para el usuario
    public static String generarToken(String email) {
        return JWT.create()
                .withSubject(email)
                .withExpiresAt(Instant.now().plus(1, ChronoUnit.HOURS)) // Token válido por 1 hora
                .sign(ALGORITMO);
    }

    // Valida un token JWT y devuelve el email del usuario si el token es válido
    public static String validarToken(String token) {
        try {
            JWTVerifier verifier = JWT.require(ALGORITMO)
                    .build();
            DecodedJWT decodedJWT = verifier.verify(token);
            return decodedJWT.getSubject(); // Devuelve el email del usuario
        } catch (Exception e) {
            return null; // El token es inválido o ha expirado
        }
    }
}
