package com.mx.Ventas.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Ventas.Entity.Autos;

@Repository
public interface AutosDao extends JpaRepository<Autos, Integer>{

}
