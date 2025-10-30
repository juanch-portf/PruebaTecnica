package com.mx.Clientes.Entity;

import java.sql.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "CLIENTES_EXA")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class Clientes {

	@Id
	@Column(name = "ID", columnDefinition = "NUMBER", nullable = false)
	int id;
	@Column(name = "NOMBRE", columnDefinition = "NVARCHAR2(50)", nullable = false)
	String nombre;
	@Column(name = "FECHA_NACIMIENTO", columnDefinition = "NVARCHAR2(50)", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:MM:ss", timezone = "America/Mexico_city")
	Date fechaNacimiento;
	String celular;
	String email;
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
	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}
	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}
	public String getCelular() {
		return celular;
	}
	public void setCelular(String celular) {
		this.celular = celular;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	

}
