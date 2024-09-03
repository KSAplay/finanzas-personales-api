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

@Path("")
public class ControladorAutenticacion {

    private UsuarioFachada usuarioFachada = new UsuarioFachada(); // Dependencia de fachada

    @POST
    @Path("register")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response registrarUsuario(Usuario usuario) {
        try {
            // Registrar nuevo usuario
            // Asegúrate de que la contraseña esté encriptada en la lógica real
            usuario.setFechaRegistro(LocalDateTime.now()); // Establece la fecha de registro actual
            usuarioFachada.registrarUsuario(usuario);
            return Response.status(Response.Status.CREATED).entity("Usuario registrado con éxito").build();
        } catch (SQLException e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity("Error al registrar usuario").build();
        }
    }

    @POST
    @Path("login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response autenticarUsuario(Credenciales credenciales) {
        try {
            Usuario usuario = usuarioFachada.obtenerUsuarioPorEmail(credenciales.getEmail());
            if (usuario != null && usuario.getPassword().equals(credenciales.getPassword())) { // Debe verificar la contraseña encriptada en un caso real
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
