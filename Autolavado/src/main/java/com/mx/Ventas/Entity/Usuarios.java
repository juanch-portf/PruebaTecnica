package com.mx.Ventas.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "USUARIO_AUTOLAVADO")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class Usuarios {

	@Id
	int id;
	String nombre;
	String email;
	String password;
	
	@OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL)
	List<Autos> listaA = new ArrayList<>();

	@Override
	public String toString() {
		return "Usuarios [id=" + id + ", nombre=" + nombre + ", email=" + email + ", password=" + password + ", listaA="
				+ listaA + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
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
	
	
	
	/*
	 * CREATE TABLE USUARIO_AUTOLAVADO ( ID NUMBER PRIMARY KEY, -- FELIPE NOMBRE
	 * VARCHAR2(100) NOT NULL, EMAIL VARCHAR2(100) UNIQUE NOT NULL, PASSWORD
	 * VARCHAR2(255) NOT NULL );
	 */
}
