package com.mx.Empleados.Dao;

import java.sql.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.mx.Empleados.Entity.Empleados;

import jakarta.transaction.Transactional;

@Repository
public interface EmpleadosDao extends JpaRepository<Empleados, Integer> {

	// listar
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM EMPLEADOS_NOM")
	public List<Empleados> listaEmpleados();

	// guardar
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "INSERT INTO EMPLEADOS_NOM VALUES(:ID,:NOMBRE,:APP,:FECHA_NACIMIENTO,:EDAD,:SEXO,:DEPTO,:SUELDO,:TIPO_CONTRATO,:DIAS_LABORALES,:FALTAS,:TOTAL_PAGO)")
	public void guardar(@Param("ID") int id,
						@Param("NOMBRE") String nombre,
						@Param("APP") String app,
						@Param("FECHA_NACIMIENTO") Date fechaNacimiento,
						@Param("EDAD") int edad,
						@Param("SEXO") String sexo,
						@Param("DEPTO") String depto,
						@Param("SUELDO") double sueldo,
						@Param("TIPO_CONTRATO") String tipoContrato,
						@Param("DIAS_LABORALES") int diasLaborales,
						@Param("FALTAS") int faltas,
						@Param("TOTAL_PAGO") double totalPago);
	
	// editar
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "UPDATE EMPLEADOS_NOM SET NOMBRE=:NOMBRE,APP=:APP,FECHA_NACIMIENTO=:FECHA_NACIMIENTO,EDAD=:EDAD,SEXO=:SEXO,DEPTO=:DEPTO,SUELDO=:SUELDO,TIPO_CONTRATO=:TIPO_CONTRATO,DIAS_LABORALES=:DIAS_LABORALES,FALTAS=:FALTAS,TOTAL_PAGO=:TOTAL_PAGO WHERE ID=:ID")
	public void editar(
						@Param("NOMBRE") String nombre,
						@Param("APP") String app,
						@Param("FECHA_NACIMIENTO") Date fechaNacimiento,
						@Param("EDAD") int edad,
						@Param("SEXO") String sexo,
						@Param("DEPTO") String depto,
						@Param("SUELDO") double sueldo,
						@Param("TIPO_CONTRATO") String tipoContrato,
						@Param("DIAS_LABORALES") int diasLaborales,
						@Param("FALTAS") int faltas,
						@Param("TOTAL_PAGO") double totalPago,
						@Param("ID") int id
						);
	
	//eliminar
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "DELETE FROM EMPLEADOS_NOM WHERE ID=:ID")
	public void eliminar(
						@Param("ID") int id
						);

	// buscar
	@Modifying
	@Transactional
	@Query(nativeQuery = true, value = "SELECT * FROM EMPLEADOS_NOM WHERE ID=:ID")
	public Empleados buscarEmpleado(@Param("ID") int id);
	
	
}

