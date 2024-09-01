package com.ksaplay.finanzas.modelos;

import java.time.LocalDate;
import java.util.Objects;

public class Transaccion {

	private int idTransaccion;
	private EnumTipoTransaccion tipo;
	private double monto;
	private LocalDate fecha;
	private Categoria categoria;

	public Transaccion() {
	}

	public Transaccion(int idTransaccion, EnumTipoTransaccion tipo, double monto, LocalDate fecha,
			Categoria categoria) {
		this.idTransaccion = idTransaccion;
		this.tipo = tipo;
		this.monto = monto;
		this.fecha = fecha;
		this.categoria = categoria;
	}

	public int getIdTransaccion() {
		return idTransaccion;
	}

	public void setIdTransaccion(int idTransaccion) {
		this.idTransaccion = idTransaccion;
	}

	public EnumTipoTransaccion getTipo() {
		return tipo;
	}

	public void setTipo(EnumTipoTransaccion tipo) {
		this.tipo = tipo;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public LocalDate getFecha() {
		return fecha;
	}

	public void setFecha(LocalDate fecha) {
		this.fecha = fecha;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idTransaccion);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Transaccion))
			return false;
		Transaccion other = (Transaccion) obj;
		return idTransaccion == other.idTransaccion;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Transaccion [idTransaccion=").append(idTransaccion).append(", tipo=").append(tipo)
				.append(", monto=").append(monto).append(", fecha=").append(fecha).append(", categoria=")
				.append(categoria).append("]");
		return builder.toString();
	}

}
