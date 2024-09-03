package com.ksaplay.finanzas.utilidades;

import java.io.IOException;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FiltroJWT implements Filter {

	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String PREFIX_BEARER = "Bearer ";

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// Inicialización del filtro, si es necesario
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpRequest = (HttpServletRequest) request;
		HttpServletResponse httpResponse = (HttpServletResponse) response;

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
					chain.doFilter(request, response); // Continuar con la solicitud
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

	@Override
	public void destroy() {
		// Limpieza de recursos si es necesario
	}
}
