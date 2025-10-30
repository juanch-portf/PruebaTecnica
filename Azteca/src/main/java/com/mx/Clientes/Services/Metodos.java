package com.mx.Clientes.Services;

import java.util.List;

import com.mx.Clientes.Entity.Clientes;

public interface Metodos {
	public void guardar(Clientes cliente);
	public void editar(Clientes cliente);
	public void eliminar(Clientes cliente);
	
	public Clientes buscarId(Clientes cliente);
	public List<Clientes> listar();

}
