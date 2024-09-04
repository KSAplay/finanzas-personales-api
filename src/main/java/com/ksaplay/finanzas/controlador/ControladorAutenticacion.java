package com.ksaplay.finanzas.controlador;

import java.sql.SQLException;
import java.time.LocalDateTime;

import com.ksaplay.finanzas.fachada.UsuarioFachada;
import com.ksaplay.finanzas.modelo.Credenciales;
import com.ksaplay.finanzas.modelo.Usuario;
import com.ksaplay.finanzas.utilidades.UtilidadesJWT;

import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

/**
 * Controlador que maneja las operaciones de autenticación y registro de usuarios.
 */
@Path("")
public class ControladorAutenticacion {

	/**
	 * Instancia de la fachada de usuario utilizada para realizar operaciones relacionadas con los usuarios.
	 */
    private UsuarioFachada usuarioFachada = new UsuarioFachada();

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param usuario El objeto {@link Usuario} con los detalles del nuevo usuario.
     * @return Una respuesta HTTP con el estado de la operación.
     */
    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario usuario) {
        try {
            usuario.setFechaRegistro(LocalDateTime.now());
            usuarioFachada.registrarUsuario(usuario);
            return Response.status(Response.Status.CREATED).entity("Usuario registrado con éxito").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al registrar usuario").build();
        }
    }

    /**
     * Autentica a un usuario y genera un token JWT.
     * 
     * @param credenciales El objeto {@link Credenciales} con el email y la contraseña del usuario.
     * @return Una respuesta HTTP con el token JWT si las credenciales son válidas, o un error si no lo son.
     */
    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(Credenciales credenciales) {
        try {
            Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(credenciales.getEmail());
            if (usuario != null && usuario.getPassword().equals(credenciales.getPassword())) {
                String token = UtilidadesJWT.generarToken(usuario.getEmail());
                return Response.ok().entity("{\"token\": \"" + token + "\"}").build();
            } else {
                return Response.status(Response.Status.UNAUTHORIZED).entity("Credenciales inválidas").build();
            }
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al autenticar usuario").build();
        }
    }
}
