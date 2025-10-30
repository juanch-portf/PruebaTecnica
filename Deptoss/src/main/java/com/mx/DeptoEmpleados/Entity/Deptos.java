package com.mx.DeptoEmpleados.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
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
@Table(name = "DEPTOS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data
public class Deptos {

	@Id
	int id;
	String nombre;
	double sueldo;
	@Column(name = "TIPO_CONTRATO", columnDefinition = "NVARCHAR2(50)")
	String tipoContrato;
	
	@OneToMany(mappedBy = "depto", cascade = CascadeType.ALL)
	List<Empleados> listaE = new ArrayList<>();

	@Override
	public String toString() {
		return "Deptos [id=" + id + ", nombre=" + nombre + ", sueldo=" + sueldo + ", tipoContrato=" + tipoContrato
				+ ", listaE=" + listaE + "]";
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
	
	
	
}
