package com.ksaplay.finanzas.fachada;

import java.sql.SQLException;
import java.util.List;

import com.ksaplay.finanzas.dao.CategoriaDAO;
import com.ksaplay.finanzas.modelo.Categoria;

/**
 * Fachada para la gestión de operaciones relacionadas con las categorias.
 * Encapsula la lógica de negocio y delega las operaciones a la capa de acceso a
 * datos.
 */
public class CategoriaFachada {

	private CategoriaDAO categoriaDAO = new CategoriaDAO();

	public void crearCategoria(Categoria nuevaCategoria) throws SQLException {
		int numeroMaximo = categoriaDAO.buscarIdCategoriaMaximo(nuevaCategoria.getIdUsuario());
		nuevaCategoria.setIdCategoria(numeroMaximo);
		categoriaDAO.insertarCategoria(nuevaCategoria);
	}

	public List<Categoria> obtenerCategorias(int idUsuario) throws SQLException {
		return categoriaDAO.buscarCategorias(idUsuario);
	}

	public boolean actualizarCategoriaPorId(Categoria categoria) throws SQLException {
		return categoriaDAO.actualizarCategoriaPorId(categoria);
	}

	public void eliminarCategoriaPorId(int idCategoria, int idUsuario) throws SQLException {
		categoriaDAO.eliminarCategoriaPorId(idCategoria, idUsuario);
	}

}
