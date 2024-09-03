package com.ksaplay.finanzas.fachada;

import java.sql.SQLException;

import com.ksaplay.finanzas.dao.UsuarioDAO;
import com.ksaplay.finanzas.modelo.Usuario;

public class UsuarioFachada {

	private UsuarioDAO usuarioDAO = new UsuarioDAO();

	// Método para registrar un nuevo usuario
	public void registrarUsuario(Usuario nuevoUsuario) throws SQLException {
		usuarioDAO.insertarUsuario(nuevoUsuario);
	}

	// Método para obtener un usuario por su email
	public Usuario obtenerUsuarioPorEmail(String email) throws SQLException {
		return usuarioDAO.buscarUsuarioPorEmail(email);
	}

	// Método para obtener un usuario por su id
	public Usuario buscarUsuarioPorId(int usuarioId) throws SQLException {
		return usuarioDAO.buscarUsuarioPorId(usuarioId);
	}
}
