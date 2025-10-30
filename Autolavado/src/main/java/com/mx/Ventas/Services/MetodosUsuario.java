package com.mx.Ventas.Services;

import java.util.List;

import com.mx.Ventas.Entity.Usuarios;

public interface MetodosUsuario {

	public void guardar(Usuarios usuario);
	public void editar(Usuarios usuario);
	public void eliminar(Usuarios usuario);
	
	public Usuarios buscar(Usuarios usuario);
	public List<Usuarios> listar();
	
}
