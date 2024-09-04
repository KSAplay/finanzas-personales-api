package com.ksaplay.finanzas.controlador;

import java.sql.SQLException;

import com.ksaplay.finanzas.fachada.UsuarioFachada;
import com.ksaplay.finanzas.modelo.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PATCH;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Controlador para manejar las operaciones relacionadas con los usuarios.
 * Proporciona endpoints para obtener, actualizar y eliminar información de
 * usuario.
 */
@Path("usuario")
public class ControladorUsuario {

	private UsuarioFachada usuarioFachada = new UsuarioFachada();

	@Context
	private HttpServletRequest request;

	/**
	 * Obtiene la información del usuario autenticado.
	 * 
	 * @return Una respuesta HTTP con el usuario autenticado o un mensaje de error
	 *         si no se encuentra el usuario.
	 */
	@GET
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response obtenerUsuario() {
		try {
			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);

			return usuario != null ? Response.ok(usuario).build()
					: Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener el usuario").build();
		}
	}

	/**
	 * Actualiza toda la información del usuario autenticado.
	 * 
	 * @param nuevoUsuario El nuevo objeto de usuario con la información
	 *                     actualizada.
	 * @return Una respuesta HTTP indicando el resultado de la actualización.
	 */
	@PUT
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarUsuarioTodo(Usuario nuevoUsuario) {
		try {
			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);

			if (usuario == null) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
			}
			nuevoUsuario.setIdUsuario(usuario.getIdUsuario());
			boolean usuarioActualizado = usuarioFachada.actualizarUsuario(nuevoUsuario);

			return usuarioActualizado ? Response.ok(nuevoUsuario).entity("Usuario actualizado").build()
					: Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el usuario")
					.build();
		}
	}

	/**
	 * Actualiza parcialmente la información del usuario autenticado.
	 * 
	 * @param nuevoUsuario El objeto de usuario con la información actualizada
	 *                     parcialmente.
	 * @return Una respuesta HTTP indicando el resultado de la actualización.
	 */
	@PATCH
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarUsuario(Usuario nuevoUsuario) {
		try {
			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);

			if (usuario == null) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
			}
			nuevoUsuario.fusionar(usuario);
			boolean usuarioActualizado = usuarioFachada.actualizarUsuario(nuevoUsuario);

			return usuarioActualizado ? Response.ok(nuevoUsuario).entity("Usuario actualizado").build()
					: Response.status(Response.Status.NOT_FOUND).entity("Usuario no encontrado").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar el usuario")
					.build();
		}
	}

	/**
	 * Elimina el usuario autenticado del sistema.
	 * 
	 * @return Una respuesta HTTP indicando si la eliminación fue exitosa o no.
	 */
	@DELETE
	@Path("")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarUsuario() {
		try {
			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);
			if (usuario == null) {
				return Response.status(Response.Status.NOT_FOUND).entity("Usuario no autorizado").build();
			}
			usuarioFachada.eliminarUsuarioPorId(usuario.getIdUsuario());
			return Response.ok("Usuario eliminado exitosamente").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al eliminar el usuario")
					.build();
		}
	}
}
