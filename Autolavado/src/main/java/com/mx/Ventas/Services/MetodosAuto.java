package com.mx.Ventas.Services;

import java.util.List;

import com.mx.Ventas.Entity.Autos;

public interface MetodosAuto {

	public void guardar(Autos auto);
	public void editar(Autos auto);
	public void eliminar(Autos auto);
	
	public Autos buscar(Autos auto);
	public List<Autos> listar();
}
