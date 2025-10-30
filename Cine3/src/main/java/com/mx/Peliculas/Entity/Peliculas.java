package com.mx.Peliculas.Entity;

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
@Table(name="PELICULAS")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
@Data

public class Peliculas {

	@Id
	@Column(name="ID", columnDefinition = "NUMBER", nullable = false)
	int id;
	@Column(name="NOMBRE_PELICULA", columnDefinition = "NVARCHAR2(50)", nullable = false)
	String nombrePelicula;
	String actor;
	String genero;
	@Column(name="FECHA_ESTRENO", columnDefinition = "DATE", nullable = false)
	@JsonFormat(pattern = "dd/MM/yyyy HH:MM:ss", timezone = "America/Mexico_City")
	Date fechaEstreno;
	String clasificacion;
	double precio;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getNombrePelicula() {
		return nombrePelicula;
	}
	public void setNombrePelicula(String nombrePelicula) {
		this.nombrePelicula = nombrePelicula;
	}
	public String getActor() {
		return actor;
	}
	public void setActor(String actor) {
		this.actor = actor;
	}
	public String getGenero() {
		return genero;
	}
	public void setGenero(String genero) {
		this.genero = genero;
	}
	public Date getFechaEstreno() {
		return fechaEstreno;
	}
	public void setFechaEstreno(Date fechaEstreno) {
		this.fechaEstreno = fechaEstreno;
	}
	public String getClasificacion() {
		return clasificacion;
	}
	public void setClasificacion(String clasificacion) {
		this.clasificacion = clasificacion;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
}
