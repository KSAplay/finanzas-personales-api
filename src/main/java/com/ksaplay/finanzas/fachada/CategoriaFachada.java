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

	public List<Categoria> obtenerCategoriasPorIdUsuario(int idUsuario) throws SQLException {
		return categoriaDAO.buscarCategoriasPorIdUsuario(idUsuario);
	}

}
