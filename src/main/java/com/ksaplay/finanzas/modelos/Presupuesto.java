package com.ksaplay.finanzas.modelos;

import java.util.Objects;

public class Presupuesto {

	private int idPresupuesto;
	private Categoria categoria;
	private double monto;
	private String mes;

	public Presupuesto() {
	}

	public Presupuesto(int idPresupuesto, Categoria categoria, double monto, String mes) {
		this.idPresupuesto = idPresupuesto;
		this.categoria = categoria;
		this.monto = monto;
		this.mes = mes;
	}

	public int getIdPresupuesto() {
		return idPresupuesto;
	}

	public void setIdPresupuesto(int idPresupuesto) {
		this.idPresupuesto = idPresupuesto;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public double getMonto() {
		return monto;
	}

	public void setMonto(double monto) {
		this.monto = monto;
	}

	public String getMes() {
		return mes;
	}

	public void setMes(String mes) {
		this.mes = mes;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idPresupuesto);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Presupuesto))
			return false;
		Presupuesto other = (Presupuesto) obj;
		return idPresupuesto == other.idPresupuesto;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Presupuesto [idPresupuesto=").append(idPresupuesto).append(", categoria=").append(categoria)
				.append(", monto=").append(monto).append(", mes=").append(mes).append("]");
		return builder.toString();
	}

}
