package com.ksaplay.finanzas.controlador;

import java.sql.SQLException;

import com.ksaplay.finanzas.fachada.UsuarioFachada;
import com.ksaplay.finanzas.modelo.Usuario;

import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("usuarios")
public class ControladorUsuario {

	private UsuarioFachada usuarioFachada = new UsuarioFachada();

	@GET
	@Path("/prueba")
	public Response prueba() {
		return Response.ok().build();
	}

	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerUsuario(@PathParam("id") int id) {
		try {
			Usuario usuario = usuarioFachada.buscarUsuarioPorId(id);
			if (usuario == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
			}
			return Response.ok(usuario).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener el usuario").build();
		}
	}

//	@PUT
//	@Path("/{id}")
//	@Consumes(MediaType.APPLICATION_JSON)
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response actualizarUsuario(@PathParam("id") int id, Usuario usuario) {
//		try {
//			Usuario usuarioExistente = usuarioFachada.buscarUsuarioPorId(id);
//			if (usuarioExistente == null) {
//				return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
//			}
//			usuario.setIdUsuario(id);
//			usuarioFachada.actualizarUsuario(usuario);
//			return Response.ok(usuario).build();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el usuario")
//					.build();
//		}
//	}
//
//	@DELETE
//	@Path("/{id}")
//	@Produces(MediaType.APPLICATION_JSON)
//	public Response eliminarUsuario(@PathParam("id") int id) {
//		try {
//			Usuario usuarioExistente = usuarioFachada.buscarUsuarioPorId(id);
//			if (usuarioExistente == null) {
//				return Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
//			}
//			usuarioFachada.eliminarUsuario(id);
//			return Response.ok("Usuario eliminado exitosamente").build();
//		} catch (Exception e) {
//			e.printStackTrace();
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el usuario")
//					.build();
//		}
//	}
}
