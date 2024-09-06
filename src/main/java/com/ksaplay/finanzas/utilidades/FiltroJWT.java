package com.ksaplay.finanzas.utilidades;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Filtro para la validación de JSON Web Tokens (JWT) en solicitudes HTTP.
 * Este filtro verifica la presencia y validez del token JWT en el encabezado de autorización.
 * Las rutas de login y registro están excluidas de la validación de JWT.
 */
public class FiltroJWT  implements Filter {

    private static final String HEADER_AUTHORIZATION = "Authorization";
    private static final String PREFIX_BEARER = "Bearer ";

    /**
     * Filtra las solicitudes HTTP para validar el token JWT.
     * 
     * @param request La solicitud HTTP que se está filtrando.
     * @param response La respuesta HTTP que se está generando.
     * @param chain La cadena de filtros a la que se pasa la solicitud y la respuesta.
     * @throws IOException Si ocurre un error de entrada/salida durante el procesamiento.
     * @throws ServletException Si ocurre un error relacionado con el servlet.
     */
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;

        String path = httpRequest.getRequestURI();

        // Excluir rutas públicas de la validación de JWT
        if (path.endsWith("/login") || path.endsWith("/register")) {
            chain.doFilter(request, response); // Permitir acceso sin token
            return;
        }

        // Obtener el token JWT del encabezado Authorization
        String authHeader = httpRequest.getHeader(HEADER_AUTHORIZATION);
        if (authHeader != null && authHeader.startsWith(PREFIX_BEARER)) {
            String token = authHeader.substring(PREFIX_BEARER.length());
            try {
                // Verificar y validar el token
                String usuarioEmail = UtilidadesJWT.validarToken(token);
                if (usuarioEmail != null) {
                    // Se puede agregar el usuario autenticado al contexto si es necesario
                    httpRequest.setAttribute("usuarioEmail", usuarioEmail);
                    // Continuar con la solicitud
                    chain.doFilter(request, response);
                    return;
                }
            } catch (Exception e) {
                // Manejar excepción de validación del token
                httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
                httpResponse.getWriter().write("Token inválido o expirado");
                return;
            }
        }

        // Si no hay token o el token es inválido, devolver un error 401
        httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        httpResponse.getWriter().write("No se proporcionó un token de autenticación");
    }

}
