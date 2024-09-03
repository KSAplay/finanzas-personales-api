package com.ksaplay.finanzas.modelo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Usuario {

	private int idUsuario;
	private String email;
	private String password;
	private String nombre;
	private LocalDateTime fechaRegistro;
	private List<Integer> transacciones;
	private List<Integer> reportes;

	public Usuario() {
	}

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
	
	public Usuario(int idUsuario, String email, String password, String nombre, LocalDateTime fechaRegistro) {
		this.idUsuario = idUsuario;
		this.email = email;
		this.password = password;
		this.nombre = nombre;
		this.fechaRegistro = fechaRegistro;
		this.transacciones = new ArrayList<>();
		this.reportes = new ArrayList<>();
	}

	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public LocalDateTime getFechaRegistro() {
		return fechaRegistro;
	}

	public void setFechaRegistro(LocalDateTime fechaRegistro) {
		this.fechaRegistro = fechaRegistro;
	}

	public List<Integer> getTransacciones() {
		return transacciones;
	}

	public void setTransacciones(List<Integer> transacciones) {
		this.transacciones = transacciones;
	}

	public List<Integer> getReportes() {
		return reportes;
	}

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

}
