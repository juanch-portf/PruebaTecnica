package com.mx.Ventas.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Ventas.Entity.Orden;

@Repository
public interface OrdenDao extends JpaRepository<Orden, Integer>{

}
