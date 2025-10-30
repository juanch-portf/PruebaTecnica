package com.mx.Clientes.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.mx.Clientes.Entity.Clientes;

@Repository
public interface IMetodos extends CrudRepository<Clientes, Integer>{

}
