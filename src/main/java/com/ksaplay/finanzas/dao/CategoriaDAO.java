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

	private final static String INSERTAR_CATEGORIA = "INSERT INTO categorias VALUES (?, ?, ?)";
	private final static String BUSCAR_ID_CATEGORIA_MAXIMO = "SELECT MAX(idCategoria) AS idMaximo FROM categorias WHERE idUsuario = ?";
	private final static String BUSCAR_CATEGORIAS = "SELECT idCategoria, nombre FROM categorias WHERE idUsuario = ? ORDER BY idCategoria";
	private final static String ACTUALIZAR_CATEGORIA_POR_ID = "UPDATE categorias SET nombre = ? WHERE idCategoria = ? AND idUsuario = ?";
	private final static String ELIMINAR_CATEGORIA_POR_ID = "DELETE FROM categorias WHERE idCategoria = ? AND idUsuario = ?";

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
	
	public Categoria insertarCategoria(Categoria categoria) throws SQLException {
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(INSERTAR_CATEGORIA);
		sentencia.setInt(1, categoria.getIdCategoria());
		sentencia.setInt(2, categoria.getIdUsuario());
		sentencia.setString(3, categoria.getNombre());
		int numFilas = sentencia.executeUpdate();
		conexion.close();
		return numFilas == 1 ? categoria : null;
	}
	
	public int buscarIdCategoriaMaximo(int idUsuario) throws SQLException {
		int idMaximo = 1;
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_ID_CATEGORIA_MAXIMO);
		sentencia.setInt(1, idUsuario);
		ResultSet resultado = sentencia.executeQuery();
		if(resultado.next()) {
			idMaximo = resultado.getInt("idMaximo") + 1;
		}
		return idMaximo;
	}

	public List<Categoria> buscarCategorias(int idUsuario) throws SQLException {
		List<Categoria> categorias = new ArrayList<>();
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(BUSCAR_CATEGORIAS);
		sentencia.setInt(1, idUsuario);

		ResultSet resultado = sentencia.executeQuery();
		while (resultado.next()) {
			categorias.add(new Categoria(resultado.getInt("idCategoria"), resultado.getString("nombre"), idUsuario));
		}
		conexion.close();
		return categorias;
	}

	public boolean actualizarCategoriaPorId(Categoria categoria) throws SQLException {
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(ACTUALIZAR_CATEGORIA_POR_ID);
		sentencia.setString(1, categoria.getNombre());
		sentencia.setInt(2, categoria.getIdCategoria());
		sentencia.setInt(3, categoria.getIdUsuario());
		int numFilas = sentencia.executeUpdate();
		conexion.close();
		return numFilas == 1;
	}

	public void eliminarCategoriaPorId(int idCategoria, int idUsuario) throws SQLException {
		Connection conexion = datasource.getConnection();
		PreparedStatement sentencia = conexion.prepareStatement(ELIMINAR_CATEGORIA_POR_ID);
		sentencia.setInt(1, idCategoria);
		sentencia.setInt(2, idUsuario);
		sentencia.executeUpdate();
		conexion.close();
	}

}
