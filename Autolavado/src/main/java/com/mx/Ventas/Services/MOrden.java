package com.mx.Ventas.Services;

import java.util.List;

import com.mx.Ventas.Entity.Orden;

public interface MOrden {
	
	public void guardar(Orden orden);
	public void editar(Orden orden);
	public void eliminar(Orden orden);
	
	public Orden buscar(Orden orden);
	public List<Orden> listar();

}
