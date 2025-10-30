package com.mx.DeptoEmpleados.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.DeptoEmpleados.Entity.Empleados;

public interface EmpleadosDao extends JpaRepository<Empleados, Integer>{

}
