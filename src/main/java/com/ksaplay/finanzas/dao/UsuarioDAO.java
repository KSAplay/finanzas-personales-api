package com.ksaplay.finanzas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksaplay.finanzas.modelo.Usuario;

public class UsuarioDAO {

	private DataSource datasource;

	private static final String INSERTAR_USUARIO = "INSERT INTO usuarios (email, password, nombre, fechaRegistro) VALUES (?, ?, ?, ?)";
	private static final String BUSCAR_USUARIO_POR_EMAIL = "SELECT * FROM usuarios WHERE email = ?";
	private static final String BUSCAR_USUARIO_POR_ID = "SELECT * FROM usuarios WHERE idUsuario = ?";

	public UsuarioDAO() {
		try {
			Context contexto = new InitialContext();
			this.datasource = (DataSource) contexto.lookup("java:comp/env/jdbc/FinanzasPersonalesDS");
		} catch (NamingException e) {
			e.printStackTrace(); // Manejo de errores adecuado en producción
		}
	}

	public void insertarUsuario(Usuario usuario) throws SQLException {
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(INSERTAR_USUARIO);
		sentencia.setString(1, usuario.getEmail());
		sentencia.setString(2, usuario.getPassword()); // Debe encriptar la contraseña en un caso real
		sentencia.setString(3, usuario.getNombre());
		sentencia.setObject(4, usuario.getFechaRegistro());
		sentencia.executeUpdate();
		conexion.close();
	}

	public Usuario buscarUsuarioPorEmail(String email) throws SQLException {
		Usuario usuario = null;
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_USUARIO_POR_EMAIL);
		sentencia.setString(1, email);
		
		ResultSet resultado = sentencia.executeQuery();
		if (resultado.next()) {
			usuario = new Usuario();
			usuario.setIdUsuario(resultado.getInt("idUsuario"));
			usuario.setEmail(resultado.getString("email"));
			usuario.setPassword(resultado.getString("password"));
			// Deberías encriptar la contraseña en la inserción y desencriptarla en el login
			usuario.setNombre(resultado.getString("nombre"));
			usuario.setFechaRegistro(resultado.getObject("fechaRegistro", java.time.LocalDateTime.class));
		}
		conexion.close();
		return usuario;
	}

	public Usuario buscarUsuarioPorId(int usuarioId) throws SQLException {
		Usuario usuario = null;
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_USUARIO_POR_ID);
		sentencia.setInt(1, usuarioId);

		ResultSet resultado = sentencia.executeQuery();
		if (resultado.next()) {
			usuario = new Usuario();
			usuario.setIdUsuario(resultado.getInt("idUsuario"));
			usuario.setEmail(resultado.getString("email"));
			usuario.setPassword(resultado.getString("password"));
			// Deberías encriptar la contraseña en la inserción y desencriptarla en el login
			usuario.setNombre(resultado.getString("nombre"));
			usuario.setFechaRegistro(resultado.getObject("fechaRegistro", java.time.LocalDateTime.class));
		}
		conexion.close();
		return usuario;
	}
}
