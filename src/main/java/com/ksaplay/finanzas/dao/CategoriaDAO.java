package com.ksaplay.finanzas.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import com.ksaplay.finanzas.modelo.Categoria;

/**
 * Data Access Object (DAO) para gestionar las operaciones CRUD sobre las
 * categorias en la base de datos.
 */
public class CategoriaDAO {

	private DataSource datasource;

	private final static String BUSCAR_CATEGORIAS_POR_ID_USUARIO = "SELECT idCategoria, nombre FROM categorias WHERE idUsuario = ?";

	/**
	 * Constructor que inicializa el DataSource para la conexión con la base de
	 * datos.
	 */
	public CategoriaDAO() {
		try {
			Context contexto = new InitialContext();
			this.datasource = (DataSource) contexto.lookup("java:comp/env/jdbc/FinanzasPersonalesDS");
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<Categoria> buscarCategoriasPorIdUsuario(int idUsuario) throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_CATEGORIAS_POR_ID_USUARIO);
		sentencia.setInt(1, idUsuario);

		ResultSet resultado = sentencia.executeQuery();
		while (resultado.next()) {
			categorias.add(new Categoria(resultado.getInt("idCategoria"), resultado.getString("nombre")));
		}
		conexion.close();
		return categorias;
	}

}
