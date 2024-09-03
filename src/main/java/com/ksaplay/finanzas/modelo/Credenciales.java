package com.ksaplay.finanzas.modelo;

import java.util.Objects;

public class Credenciales {

	private String email;
	private String password;

	public Credenciales() {}
	
	public Credenciales(String email, String password) {
		this.email = email;
		this.password = password;
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

	@Override
	public int hashCode() {
		return Objects.hash(email, password);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!(obj instanceof Credenciales))
			return false;
		Credenciales other = (Credenciales) obj;
		return Objects.equals(email, other.email) && Objects.equals(password, other.password);
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Credenciales [email=").append(email).append(", password=").append(password).append("]");
		return builder.toString();
	}

}
