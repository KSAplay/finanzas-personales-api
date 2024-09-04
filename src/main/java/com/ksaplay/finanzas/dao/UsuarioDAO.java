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

/**
 * Data Access Object (DAO) para gestionar las operaciones CRUD sobre los
 * usuarios en la base de datos.
 */
public class UsuarioDAO {

	private DataSource datasource;

	private static final String INSERTAR_USUARIO = "INSERT INTO usuarios (email, password, nombre, fechaRegistro) VALUES (?, ?, ?, ?)";
	private static final String BUSCAR_USUARIO_POR_EMAIL = "SELECT * FROM usuarios WHERE email = ?";
	private static final String BUSCAR_USUARIO_POR_ID = "SELECT * FROM usuarios WHERE idUsuario = ?";
	private static final String BUSCAR_TRANSACCIONES_POR_ID_USUARIO = "SELECT idTransaccion FROM transacciones WHERE idUsuario = ?";
	private static final String BUSCAR_REPORTES_POR_ID_USUARIO = "SELECT idReporte FROM reportes WHERE idUsuario = ?";
	private static final String ACTUALIZAR_USUARIO = "UPDATE usuarios SET email = ?, nombre = ?, password = ? WHERE idUsuario = ?";
	private static final String ELIMINAR_USUARIO = "DELETE FROM usuarios WHERE idUsuario = ?";

	/**
	 * Constructor que inicializa el DataSource para la conexión con la base de
	 * datos.
	 */
	public UsuarioDAO() {
		try {
			Context contexto = new InitialContext();
			this.datasource = (DataSource) contexto.lookup("java:comp/env/jdbc/FinanzasPersonalesDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Inserta un nuevo usuario en la base de datos.
	 * 
	 * @param usuario El objeto de usuario a insertar.
	 * @throws SQLException Si ocurre un error durante la operación de base de
	 *                      datos.
	 */
	public void insertarUsuario(Usuario usuario) throws SQLException {
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(INSERTAR_USUARIO);
		sentencia.setString(1, usuario.getEmail());
		sentencia.setString(2, usuario.getPassword());
		sentencia.setString(3, usuario.getNombre());
		sentencia.setObject(4, usuario.getFechaRegistro());
		sentencia.executeUpdate();
		conexion.close();
	}

	/**
	 * Busca un usuario en la base de datos por su correo electrónico.
	 * 
	 * @param email El correo electrónico del usuario a buscar.
	 * @return El objeto de usuario encontrado o {@code null} si no se encuentra.
	 * @throws SQLException Si ocurre un error durante la operación de base de
	 *                      datos.
	 */
	public Usuario buscarUsuarioPorEmail(String email) throws SQLException {
		Usuario usuario = null;
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_USUARIO_POR_EMAIL);
		sentencia.setString(1, email);

		ResultSet resultado = sentencia.executeQuery();
		if (resultado.next()) {
			// Creación del objeto usuario
			usuario = new Usuario(resultado.getInt("idUsuario"), resultado.getString("email"),
					resultado.getString("password"), resultado.getString("nombre"),
					resultado.getObject("fechaRegistro", java.time.LocalDateTime.class));
			// Busqueda de las transacciones del usuario
			PreparedStatement sentenciaT = conexion.prepareStatement(BUSCAR_TRANSACCIONES_POR_ID_USUARIO);
			sentenciaT.setInt(1, usuario.getIdUsuario());
			ResultSet resultadoT = sentenciaT.executeQuery();
			while (resultadoT.next()) {
				usuario.insertarTransaccion(resultadoT.getInt("idTransaccion"));
			}
			// Busqueda de los reportes del usuario
			PreparedStatement sentenciaR = conexion.prepareStatement(BUSCAR_REPORTES_POR_ID_USUARIO);
			sentenciaR.setInt(1, usuario.getIdUsuario());
			ResultSet resultadoR = sentenciaR.executeQuery();
			while (resultadoR.next()) {
				usuario.insertarReporte(resultadoR.getInt("idReporte"));
			}
		}
		conexion.close();
		return usuario;
	}

	/**
	 * Busca un usuario en la base de datos por su identificador.
	 * 
	 * @param usuarioId El identificador del usuario a buscar.
	 * @return El objeto de usuario encontrado o {@code null} si no se encuentra.
	 * @throws SQLException Si ocurre un error durante la operación de base de
	 *                      datos.
	 */
	public Usuario buscarUsuarioPorId(int usuarioId) throws SQLException {
		Usuario usuario = null;
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_USUARIO_POR_ID);
		sentencia.setInt(1, usuarioId);

		ResultSet resultado = sentencia.executeQuery();
		if (resultado.next()) {
			usuario = new Usuario(resultado.getInt("idUsuario"), resultado.getString("email"),
					resultado.getString("password"), resultado.getString("nombre"),
					resultado.getObject("fechaRegistro", java.time.LocalDateTime.class));
		}
		conexion.close();
		return usuario;
	}

	/**
	 * Actualiza la información de un usuario en la base de datos por su
	 * identificador.
	 * 
	 * @param usuario El objeto de usuario con la información actualizada.
	 * @return {@code true} si la actualización fue exitosa, {@code false} en caso
	 *         contrario.
	 * @throws SQLException Si ocurre un error durante la operación de base de
	 *                      datos.
	 */
	public boolean actualizarUsuarioPorId(Usuario usuario) throws SQLException {
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(ACTUALIZAR_USUARIO);
		sentencia.setString(1, usuario.getEmail());
		sentencia.setString(2, usuario.getNombre());
		sentencia.setString(3, usuario.getPassword());
		sentencia.setInt(4, usuario.getIdUsuario());
		int numFilas = sentencia.executeUpdate();
		conexion.close();
		return numFilas == 1;
	}

	/**
	 * Elimina un usuario de la base de datos por su identificador.
	 * 
	 * @param idUsuario El identificador del usuario a eliminar.
	 * @throws SQLException Si ocurre un error durante la operación de base de
	 *                      datos.
	 */
	public void eliminarUsuarioPorId(int idUsuario) throws SQLException {
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(ELIMINAR_USUARIO);
		sentencia.setInt(1, idUsuario);
		sentencia.executeUpdate();
		conexion.close();
	}
}
