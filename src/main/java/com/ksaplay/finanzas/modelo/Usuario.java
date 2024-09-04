package com.ksaplay.finanzas.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Representa un usuario del sistema. Contiene información sobre el usuario,
 * incluyendo su identificador, correo electrónico, contraseña, nombre, fecha de
 * registro, transacciones y reportes.
 */
public class Usuario {

	private int idUsuario;
	private String email;
	private String password;
	private String nombre;
	private LocalDateTime fechaRegistro;
	private List<Integer> transacciones;
	private List<Integer> reportes;

	/**
	 * Constructor por defecto.
	 */
	public Usuario() {
	}

	/**
	 * Constructor con todos los campos.
	 * 
	 * @param idUsuario     El identificador del usuario.
	 * @param email         El correo electrónico del usuario.
	 * @param password      La contraseña del usuario.
	 * @param nombre        El nombre del usuario.
	 * @param fechaRegistro La fecha de registro del usuario.
	 * @param transacciones La lista de identificadores de las transacciones del
	 *                      usuario.
	 * @param reportes      La lista de identificadores de los reportes del usuario.
	 */
	public Usuario(int idUsuario, String email, String password, String nombre, LocalDateTime fechaRegistro,
			List<Integer> transacciones, List<Integer> reportes) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.transacciones = transacciones;
		this.reportes = reportes;
	}

	/**
	 * Constructor con los campos obligatorios.
	 * 
	 * @param idUsuario     El identificador del usuario.
	 * @param email         El correo electrónico del usuario.
	 * @param password      La contraseña del usuario.
	 * @param nombre        El nombre del usuario.
	 * @param fechaRegistro La fecha de registro del usuario.
	 */
	public Usuario(int idUsuario, String email, String password, String nombre, LocalDateTime fechaRegistro) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.transacciones = new ArrayList<>();
		this.reportes = new ArrayList<>();
	}

	/**
	 * Obtiene el identificador del usuario.
	 * 
	 * @return El identificador del usuario.
	 */
	public int getIdUsuario() {
		return idUsuario;
	}

	/**
	 * Establece el identificador del usuario.
	 * 
	 * @param idUsuario El identificador del usuario.
	 */
	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	/**
	 * Obtiene el correo electrónico del usuario.
	 * 
	 * @return El correo electrónico del usuario.
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Establece el correo electrónico del usuario.
	 * 
	 * @param email El correo electrónico del usuario.
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Obtiene la contraseña del usuario.
	 * 
	 * @return La contraseña del usuario.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Establece la contraseña del usuario.
	 * 
	 * @param password La contraseña del usuario.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Obtiene el nombre del usuario.
	 * 
	 * @return El nombre del usuario.
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Establece el nombre del usuario.
	 * 
	 * @param nombre El nombre del usuario.
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * Obtiene la fecha de registro del usuario.
	 * 
	 * @return La fecha de registro del usuario.
	 */
	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	/**
	 * Establece la fecha de registro del usuario.
	 * 
	 * @param fechaRegistro La fecha de registro del usuario.
	 */
	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	/**
	 * Obtiene la lista de identificadores de las transacciones del usuario.
	 * 
	 * @return La lista de identificadores de las transacciones del usuario.
	 */
	public List<Integer> getTransacciones() {
		return transacciones;
	}

	/**
	 * Establece la lista de identificadores de las transacciones del usuario.
	 * 
	 * @param transacciones La lista de identificadores de las transacciones del
	 *                      usuario.
	 */
	public void setTransacciones(List<Integer> transacciones) {
		this.transacciones = transacciones;
	}

	/**
	 * Obtiene la lista de identificadores de los reportes del usuario.
	 * 
	 * @return La lista de identificadores de los reportes del usuario.
	 */
	public List<Integer> getReportes() {
		return reportes;
	}

	/**
	 * Establece la lista de identificadores de los reportes del usuario.
	 * 
	 * @param reportes La lista de identificadores de los reportes del usuario.
	 */
	public void setReportes(List<Integer> reportes) {
		this.reportes = reportes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idUsuario);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Usuario))
			return false;
		Usuario other = (Usuario) obj;
		return idUsuario == other.idUsuario;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Usuario [idUsuario=").append(idUsuario).append(", email=").append(email).append(", password=")
				.append(password).append(", nombre=").append(nombre).append(", fechaRegistro=").append(fechaRegistro)
				.append(", transacciones=").append(transacciones).append(", reportes=").append(reportes).append("]");
		return builder.toString();
	}

	/**
	 * Fusiona los datos del usuario actual con los datos de otro usuario.
	 * 
	 * @param usuario El usuario con el que se fusionan los datos.
	 */
	public void fusionar(Usuario usuario) {
		this.idUsuario = usuario.idUsuario;

		if (this.email == null) {
			this.email = usuario.email;
		}
		if (this.password == null) {
			this.password = usuario.password;
		}
		if (this.nombre == null) {
			this.nombre = usuario.nombre;
		}
	}

	/**
	 * Inserta un identificador de transacción en la lista de transacciones del
	 * usuario.
	 * 
	 * @param idTransaccion El identificador de la transacción a insertar.
	 */
	public void insertarTransaccion(int idTransaccion) {
		this.transacciones.add(idTransaccion);
	}

	/**
	 * Elimina un identificador de transacción de la lista de transacciones del
	 * usuario.
	 * 
	 * @param idTransaccion El identificador de la transacción a eliminar.
	 */
	public void eliminarTransaccion(int idTransaccion) {
		this.transacciones.remove(idTransaccion);
	}

	/**
	 * Inserta un identificador de reporte en la lista de reportes del usuario.
	 * 
	 * @param idReporte El identificador del reporte a insertar.
	 */
	public void insertarReporte(int idReporte) {
		this.reportes.add(idReporte);
	}

	/**
	 * Elimina un identificador de reporte de la lista de reportes del usuario.
	 * 
	 * @param idReporte El identificador del reporte a eliminar.
	 */
	public void eliminarReporte(int idReporte) {
		this.reportes.remove(idReporte);
	}

}
