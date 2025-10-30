package com.mx.DeptoEmpleados.Services;

import java.util.List;

import com.mx.DeptoEmpleados.Entity.Empleados;

public interface EMetodos {

	public void guardar(Empleados empleado);
	public void editar(Empleados empleado);
	public void eliminar(Empleados empleado);
	
	public List<Empleados> listar();
	public Empleados buscar(Empleados empleado);
}
