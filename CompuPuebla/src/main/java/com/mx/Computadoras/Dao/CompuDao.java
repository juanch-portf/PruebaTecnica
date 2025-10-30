package com.mx.Computadoras.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.Computadoras.Entity.Computadoras;

import jakarta.transaction.Transactional;

@Repository
public interface CompuDao extends JpaRepository<Computadoras, Integer>{
	
	//listar
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM COMPUTADORAS_SEP2025")
	public List<Computadoras> listaCompu();
	
	//guardar
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO COMPUTADORAS_SEP2025 VALUES(:ID,:MARCA,:MODELO,:COLOR,:CAPACIDAD,:FECHA_VENTA,:CANTIDAD,:PRECIO)")
	public void guardar(
						@Param("ID") int id,
						@Param("MARCA") String marca,
						@Param("MODELO") String modelo,
						@Param("COLOR") String color,
						@Param("CAPACIDAD") int capacidad,
						@Param("FECHA_VENTA") Date fechaVenta,
						@Param("CANTIDAD") int cantidad,
						@Param("PRECIO") double precio
						);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE COMPUTADORAS_SEP2025 SET MARCA=:MARCA,MODELO=:MODELO,COLOR=:COLOR,CAPACIDAD=:CAPACIDAD,FECHA_VENTA=:FECHA_VENTA,CANTIDAD=:CANTIDAD,PRECIO=:PRECIO WHERE ID=:ID")
	public void editar(
						@Param("MARCA") String marca,
						@Param("MODELO") String modelo,
						@Param("COLOR") String color,
						@Param("CAPACIDAD") int capacidad,
						@Param("FECHA_VENTA") Date fechaVenta,
						@Param("CANTIDAD") int cantidad,
						@Param("PRECIO") double precio,
						@Param("ID") int id
						);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM COMPUTADORAS_SEP2025 WHERE ID=:ID")
	public void eliminar(
						@Param("ID") int id
						);
	
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM COMPUTADORAS_SEP2025 WHERE ID=:ID")
	public Computadoras buscarCompu(@Param("ID") int id);
	
	
}
