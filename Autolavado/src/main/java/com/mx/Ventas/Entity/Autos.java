package com.mx.Ventas.Entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name = "AUTO_AUTOLAVADO")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Data
public class Autos {
	
	@Id
	int id;
	String marca;
	String modelo;
	String color;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "USUARIO_ID")
	Usuarios usuario;
	
	@OneToMany(mappedBy = "auto", cascade = CascadeType.ALL)
	List<Orden> listaOrd = new ArrayList<>();

	@Override
	public String toString() {
		return "Autos [id=" + id + ", marca=" + marca + ", modelo=" + modelo + ", color=" + color + ", usuario="
				+ usuario + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getMarca() {
		return marca;
	}

	public void setMarca(String marca) {
		this.marca = marca;
	}

	public String getModelo() {
		return modelo;
	}

	public void setModelo(String modelo) {
		this.modelo = modelo;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public Usuarios getUsuario() {
		return usuario;
	}

	public void setUsuario(Usuarios usuario) {
		this.usuario = usuario;
	}
	
	
	
	/*CREATE TABLE AUTO_AUTOLAVADO (                                 --Usuario <-> Auto   ONE TO MANY
    ID              NUMBER PRIMARY KEY,
    MARCA           VARCHAR2(50) NOT NULL,
    MODELO          VARCHAR2(50) NOT NULL,
    COLOR           VARCHAR2(30) NOT NULL,
    USUARIO_ID      NUMBER NOT NULL,
    CONSTRAINT FK_AUTO_USUARIO FOREIGN KEY (USUARIO_ID) REFERENCES USUARIO_AUTOLAVADO(ID)
	);
	 * */

}
