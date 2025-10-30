package com.mx.Ventas.Entity;

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
@Table(name = "ORDEN_SERVICIO_AUTOLAVADO")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Setter
@Getter
@Data
public class Orden {
	
	@Id
	int id;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "AUTO_ID")
	Autos auto;
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "SERVICIO_ID")
	Servicio serv;
	
	@Column(name = "FECHA_INGRESO", columnDefinition = "TIMESTAMP", nullable = true)
	Date fechaIngreso;
	@Column(name = "FECHA_SALIDA", columnDefinition = "TIMESTAMP", nullable = false)
	Date fechaSalida;
	@Column(name = "HORAS_EN_TALLER", columnDefinition = "NUMBER(5,2")
	double horasTaller;
	double precio;
	@Column(name = "ESTADO_PAGO", columnDefinition = "VARCHAR2(20)",nullable = false)
	String estadoPago;
	@Column(name = "METODO_PAGO", columnDefinition = "VARCHAR2(30")
	String metodoPago;
	@Override
	public String toString() {
		return "Orden [id=" + id + ", auto=" + auto + ", serv=" + serv + ", fechaIngreso=" + fechaIngreso
				+ ", fechaSalida=" + fechaSalida + ", horasTaller=" + horasTaller + ", precio=" + precio
				+ ", estadoPago=" + estadoPago + ", metodoPago=" + metodoPago + "]";
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Autos getAuto() {
		return auto;
	}
	public void setAuto(Autos auto) {
		this.auto = auto;
	}
	public Servicio getServ() {
		return serv;
	}
	public void setServ(Servicio serv) {
		this.serv = serv;
	}
	public Date getFechaIngreso() {
		return fechaIngreso;
	}
	public void setFechaIngreso(Date fechaIngreso) {
		this.fechaIngreso = fechaIngreso;
	}
	public Date getFechaSalida() {
		return fechaSalida;
	}
	public void setFechaSalida(Date fechaSalida) {
		this.fechaSalida = fechaSalida;
	}
	public double getHorasTaller() {
		return horasTaller;
	}
	public void setHorasTaller(double horasTaller) {
		this.horasTaller = horasTaller;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public String getEstadoPago() {
		return estadoPago;
	}
	public void setEstadoPago(String estadoPago) {
		this.estadoPago = estadoPago;
	}
	public String getMetodoPago() {
		return metodoPago;
	}
	public void setMetodoPago(String metodoPago) {
		this.metodoPago = metodoPago;
	}
	
	
	
	/*CREATE TABLE ORDEN_SERVICIO_AUTOLAVADO (                               --orden servicio <-> Auto        MANY TO ONE
    ID                  NUMBER PRIMARY KEY,                            --Orden Servicio <-> Servicio    MANY TO ONE
    AUTO_ID             NUMBER NOT NULL,                               
    SERVICIO_ID         NUMBER NOT NULL,
    FECHA_INGRESO       TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    FECHA_SALIDA        TIMESTAMP,
    HORAS_EN_TALLER     NUMBER(5,2),
    PRECIO              NUMBER(10,2),
    ESTADO_PAGO              VARCHAR2(20) DEFAULT 'PENDIENTE' CHECK (ESTADO_PAGO IN ('PENDIENTE', 'PAGADO')),
    METODO_PAGO         VARCHAR2(30) CHECK (METODO_PAGO IN ('MERCADO_PAGO', 'TARJETA', 'EFECTIVO')),
    CONSTRAINT FK_ORDEN_AUTO FOREIGN KEY (AUTO_ID) REFERENCES AUTO_AUTOLAVADO(ID),
    CONSTRAINT FK_ORDEN_SERVICIO FOREIGN KEY (SERVICIO_ID) REFERENCES SERVICIO_AUTOLAVADO(ID)
);
	 * 
	 */

}
