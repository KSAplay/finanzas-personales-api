package com.ksaplay.finanzas.controlador;

import java.sql.SQLException;
import java.util.List;

import com.ksaplay.finanzas.fachada.CategoriaFachada;
import com.ksaplay.finanzas.fachada.UsuarioFachada;
import com.ksaplay.finanzas.modelo.Categoria;
import com.ksaplay.finanzas.modelo.Usuario;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.DELETE;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.PathParam;
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
	public Response obtenerCategorias() {
		try {
			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);

			if (usuario == null) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
			}

			List<Categoria> categorias = categoriaFachada.obtenerCategorias(usuario.getIdUsuario());
			return Response.ok(categorias).build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al obtener las categorias")
					.build();
		}
	}

	@POST
	@Path("")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response crearCategoria(Categoria nuevaCategoria) {
		try {
			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");

			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);
			if (usuario == null) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
			}

			nuevaCategoria.setIdUsuario(usuario.getIdUsuario());
			categoriaFachada.crearCategoria(nuevaCategoria);
			return Response.ok(Response.Status.CREATED).entity("Categoria creada exitosamente").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al crear la categoría").build();
		}
	}

	@PUT
	@Path("/{idCategoria}")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response actualizarCategoriasPorId(@PathParam("idCategoria") String idCategoriaString,
			Categoria nuevaCategoria) {
		try {
			int idCategoria = Integer.parseInt(idCategoriaString);

			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);
			if (usuario == null) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
			}

			nuevaCategoria.setIdCategoria(idCategoria);
			nuevaCategoria.setIdUsuario(usuario.getIdUsuario());
			boolean categoriaActualizada = categoriaFachada.actualizarCategoriaPorId(nuevaCategoria);
			return categoriaActualizada ? Response.ok().entity("Categoria actualizada").build()
					: Response.status(Response.Status.NOT_FOUND).entity("Categoria no encontrada").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar la categoria")
					.build();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("El ID de la categoría debe ser un número válido").build();
		}
	}

	@DELETE
	@Path("/{idCategoria}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response eliminarCategoriaPorId(@PathParam("idCategoria") String idCategoriaString) {
		try {
			int idCategoria = Integer.parseInt(idCategoriaString);

			String usuarioEmailToken = (String) request.getAttribute("usuarioEmail");
			Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(usuarioEmailToken);
			if (usuario == null) {
				return Response.status(Response.Status.UNAUTHORIZED).entity("Usuario no autorizado").build();
			}

			categoriaFachada.eliminarCategoriaPorId(idCategoria, usuario.getIdUsuario());
			return Response.ok("Categoría eliminada exitosamente").build();
		} catch (SQLException e) {
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al actualizar la categoria")
					.build();
		} catch (NumberFormatException e) {
			e.printStackTrace();
			return Response.status(Response.Status.BAD_REQUEST)
					.entity("El ID de la categoría debe ser un número válido").build();
		}
	}
}
