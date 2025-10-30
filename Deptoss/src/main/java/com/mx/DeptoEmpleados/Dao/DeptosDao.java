package com.mx.DeptoEmpleados.Dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.mx.DeptoEmpleados.Entity.Deptos;

public interface DeptosDao extends JpaRepository<Deptos, Integer> {

	
}
