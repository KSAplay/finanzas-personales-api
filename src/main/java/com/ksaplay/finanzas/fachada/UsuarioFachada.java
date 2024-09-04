package com.ksaplay.finanzas.fachada;

import java.sql.SQLException;

import com.ksaplay.finanzas.dao.UsuarioDAO;
import com.ksaplay.finanzas.modelo.Usuario;

/**
 * Fachada para la gestión de operaciones relacionadas con los usuarios.
 * Encapsula la lógica de negocio y delega las operaciones a la capa de acceso a datos.
 */
public class UsuarioFachada {

    private UsuarioDAO usuarioDAO = new UsuarioDAO();

    /**
     * Registra un nuevo usuario en el sistema.
     * 
     * @param nuevoUsuario El usuario que se desea registrar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void registrarUsuario(Usuario nuevoUsuario) throws SQLException {
        usuarioDAO.insertarUsuario(nuevoUsuario);
    }

    /**
     * Obtiene un usuario basado en su dirección de correo electrónico.
     * 
     * @param email La dirección de correo electrónico del usuario.
     * @return El usuario asociado al correo electrónico proporcionado.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public Usuario obtenerUsuarioPorEmail(String email) throws SQLException {
        return usuarioDAO.buscarUsuarioPorEmail(email);
    }

    /**
     * Actualiza la información de un usuario en el sistema.
     * 
     * @param usuario El usuario con la información actualizada.
     * @return true si la actualización fue exitosa, false de lo contrario.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public boolean actualizarUsuario(Usuario usuario) throws SQLException {
        return usuarioDAO.actualizarUsuarioPorId(usuario);
    }

    /**
     * Elimina un usuario del sistema basado en su identificador.
     * 
     * @param idUsuario El identificador del usuario a eliminar.
     * @throws SQLException Si ocurre un error al interactuar con la base de datos.
     */
    public void eliminarUsuarioPorId(int idUsuario) throws SQLException {
        usuarioDAO.eliminarUsuarioPorId(idUsuario);
    }
}
