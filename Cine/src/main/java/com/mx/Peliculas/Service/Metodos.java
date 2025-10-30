package com.mx.Peliculas.Service;

import java.util.List;

import com.mx.Peliculas.Entity.Peliculas;

public interface Metodos {
	public void guardar(Peliculas peli);
	public void editar(Peliculas peli);
	public void eliminar(Peliculas peli);
	
	public Peliculas buscarId(Peliculas peli);
	public List<Peliculas> listar();
	public List<Peliculas> listar(Peliculas peli);
	

}
