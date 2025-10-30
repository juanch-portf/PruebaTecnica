package com.mx.Clientes.Services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Clientes.Dao.IMetodos;
import com.mx.Clientes.Entity.Clientes;

@Service
public class Implementacion implements Metodos{

	@Autowired
	IMetodos dao;

	@Override
	public void guardar(Clientes cliente) {
		dao.save(cliente);
		
	}

	@Override
	public void editar(Clientes cliente) {
		dao.save(cliente);
		
	}

	@Override
	public void eliminar(Clientes cliente) {
		dao.deleteById(cliente.getId());
		
	}

	@Override
	public Clientes buscarId(Clientes cliente) {
		return dao.findById(cliente.getId()).orElse(null);
	}

	@Override
	public List<Clientes> listar() {
		return (List<Clientes>) dao.findAll();
	}
	

}
