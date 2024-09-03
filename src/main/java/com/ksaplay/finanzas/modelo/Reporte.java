package com.ksaplay.finanzas.modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Reporte {

	private int idReporte;
	private LocalDate desde;
	private LocalDate hasta;
	private double totalIngresos;
	private double totalGastos;
	private double saldo;

	public Reporte() {
	}

	public Reporte(int idReporte, LocalDate desde, LocalDate hasta, double totalIngresos, double totalGastos,
			double saldo) {
		this.idReporte = idReporte;
		this.desde = desde;
		this.hasta = hasta;
		this.totalIngresos = totalIngresos;
		this.totalGastos = totalGastos;
		this.saldo = saldo;
	}

	public int getIdReporte() {
		return idReporte;
	}

	public void setIdReporte(int idReporte) {
		this.idReporte = idReporte;
	}

	public LocalDate getDesde() {
		return desde;
	}

	public void setDesde(LocalDate desde) {
		this.desde = desde;
	}

	public LocalDate getHasta() {
		return hasta;
	}

	public void setHasta(LocalDate hasta) {
		this.hasta = hasta;
	}

	public double getTotalIngresos() {
		return totalIngresos;
	}

	public void setTotalIngresos(double totalIngresos) {
		this.totalIngresos = totalIngresos;
	}

	public double getTotalGastos() {
		return totalGastos;
	}

	public void setTotalGastos(double totalGastos) {
		this.totalGastos = totalGastos;
	}

	public double getSaldo() {
		return saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	@Override
	public int hashCode() {
		return Objects.hash(idReporte);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Reporte))
			return false;
		Reporte other = (Reporte) obj;
		return idReporte == other.idReporte;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Reporte [idReporte=").append(idReporte).append(", desde=").append(desde).append(", hasta=")
				.append(hasta).append(", totalIngresos=").append(totalIngresos).append(", totalGastos=")
				.append(totalGastos).append(", saldo=").append(saldo).append("]");
		return builder.toString();
	}

}
