package com.ksaplay.finanzas.controlador;

import java.sql.SQLException;
import java.util.List;

import com.ksaplay.finanzas.fachada.CategoriaFachada;
import com.ksaplay.finanzas.fachada.UsuarioFachada;
import com.ksaplay.finanzas.modelo.Categoria;
import com.ksaplay.finanzas.modelo.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("categorias")
public class ControladorCategoria {

	private CategoriaFachada categoriaFachada = new CategoriaFachada();
	private UsuarioFachada usuarioFachada = new UsuarioFachada();

	@Context
	private HttpServletRequest request;
	
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerCategoriasPorIdUsuario() {
		try {
			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);
			
			if (usuario == null) { 
				Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
			}
			List<Categoria> categorias = categoriaFachada.obtenerCategoriasPorIdUsuario(usuario.getIdUsuario());
			return Response.ok(categorias).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener las categorias").build();
		}
	}
}
