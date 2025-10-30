package com.mx.DeptoEmpleados.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLEADOS_MANY")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class Empleados {

	@Id
	int id;
	String nombre;
	String app;
	@Column(name = "FECHA_NACIMIENTO", columnDefinition = "DATE", nullable = false)
	Date fechaNacimiento;
	int edad;
	String sexo;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "ID_DEPTO")
	Deptos depto;

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

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public Date getFechaNacimiento() {
		return fechaNacimiento;
	}

	public void setFechaNacimiento(Date fechaNacimiento) {
		this.fechaNacimiento = fechaNacimiento;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public String getSexo() {
		return sexo;
	}

	public void setSexo(String sexo) {
		this.sexo = sexo;
	}

	public Deptos getDepto() {
		return depto;
	}

	public void setDepto(Deptos depto) {
		this.depto = depto;
	}
}
