package com.mx.Ventas.Dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.mx.Ventas.Entity.Usuarios;

@Repository
public interface UserDao extends JpaRepository<Usuarios, Integer>{

}
