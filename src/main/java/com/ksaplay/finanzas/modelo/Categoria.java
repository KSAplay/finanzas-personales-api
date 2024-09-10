package com.ksaplay.finanzas.modelo;

import java.util.Objects;

public class Categoria {

	private int idCategoria;
	private String nombre;
	private int idUsuario;

	public Categoria() {
	}

	public Categoria(int idCategoria, String nombre, int idUsuario) {
		this.idCategoria = idCategoria;
		this.nombre = nombre;
		this.setIdUsuario(idUsuario);
	}

	public int getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(int idCategoria) {
		this.idCategoria = idCategoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	
	public int getIdUsuario() {
		return idUsuario;
	}

	public void setIdUsuario(int idUsuario) {
		this.idUsuario = idUsuario;
	}


	@Override
	public int hashCode() {
		return Objects.hash(idCategoria);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Categoria))
			return false;
		Categoria other = (Categoria) obj;
		return idCategoria == other.idCategoria;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Categoria [idCategoria=").append(idCategoria).append(", nombre=").append(nombre)
				.append(", idUsuario=").append(idUsuario).append("]");
		return builder.toString();
	}
	
}
