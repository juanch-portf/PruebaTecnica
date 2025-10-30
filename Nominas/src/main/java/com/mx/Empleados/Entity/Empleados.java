package com.mx.Empleados.Entity;

import java.sql.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "EMPLEADOS_NOM", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"NOMBRE", "APP"}) // evitar duplicados
})
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
	@Column(name= "FECHA_NACIMIENTO", columnDefinition = "DATE", nullable = false)
	Date fechaNacimiento;
	int edad;
	String sexo;
	String Depto;
	double sueldo;
	@Column(name= "TIPO_CONTRATO", columnDefinition = "NVARCHAR2(50)", nullable = false)
	String tipoContrato;
	@Column(name= "DIAS_LABORALES", columnDefinition = "NUMBER", nullable = false)
	int diasLaborales;
	int faltas;
	@Column(name= "TOTAL_PAGO", columnDefinition = "NUMBER", nullable = false)
	double totalPago;
	
	@Override
	public String toString() {
		return "Empleados [id=" + id + ", nombre=" + nombre + ", app=" + app + ", fechaNacimiento=" + fechaNacimiento
				+ ", edad=" + edad + ", sexo=" + sexo + ", Depto=" + Depto + ", sueldo=" + sueldo + ", tipoContrato="
				+ tipoContrato + ", diasLaborales=" + diasLaborales + ", faltas=" + faltas + ", totalPago=" + totalPago
				+ "]";
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
	public String getDepto() {
		return Depto;
	}
	public void setDepto(String depto) {
		Depto = depto;
	}
	public double getSueldo() {
		return sueldo;
	}
	public void setSueldo(double sueldo) {
		this.sueldo = sueldo;
	}
	public String getTipoContrato() {
		return tipoContrato;
	}
	public void setTipoContrato(String tipoContrato) {
		this.tipoContrato = tipoContrato;
	}
	public int getDiasLaborales() {
		return diasLaborales;
	}
	public void setDiasLaborales(int diasLaborales) {
		this.diasLaborales = diasLaborales;
	}
	public int getFaltas() {
		return faltas;
	}
	public void setFaltas(int faltas) {
		this.faltas = faltas;
	}
	public double getTotalPago() {
		return totalPago;
	}
	public void setTotalPago(double totalPago) {
		this.totalPago = totalPago;
	}
	
	


	
	/*
	 * CREATE TABLE EMPLEADOS_NOM ( ID NUMBER, APP VARCHAR2(50), FECHA_NACIMIENTO
	 * DATE, EDAD NUMBER, SEXO VARCHAR2(50), DEPTO VARCHAR2(50), SUELDO NUMBER,
	 * TIPO_CONTRATO VARCHAR2(50), DIAS_LABORALES NUMBER, FALTAS NUMBER, TOTAL_PAGO
	 * NUMBER );
	 */

}
