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
@Table(name = "SERVICIO_AUTOLAVADO")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Data
public class Servicio {

	@Id
	int id;
	String nombre;
	double precio;
	
	@OneToMany(mappedBy = "serv", cascade = CascadeType.ALL )
	List<Orden> listaO = new ArrayList<>();

	@Override
	public String toString() {
		return "Servicio [id=" + id + ", nombre=" + nombre + ", precio=" + precio + ", listaO=" + listaO + "]";
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

	public double getPrecio() {
		return precio;
	}

	public void setPrecio(double precio) {
		this.precio = precio;
	}
	
	
	
	
	/*CREATE TABLE SERVICIO_AUTOLAVADO (
    ID              NUMBER  PRIMARY KEY,
    NOMBRE          VARCHAR2(100) NOT NULL,
    PRECIO          NUMBER(10,2) NOT NULL
);
	 * */
}
