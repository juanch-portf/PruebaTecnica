package com.mx.DeptoEmpleados.Services;

import java.util.List;

import com.mx.DeptoEmpleados.Entity.Deptos;

public interface DMetodos{

	public void guardar(Deptos depto);
	public void editar(Deptos depto);
	public void eliminar(Deptos depto);
	
	public List<Deptos> listar();
	public Deptos buscar(Deptos depto);
}
