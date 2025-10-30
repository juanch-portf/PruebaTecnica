package com.mx.Computadoras.Service;

import java.util.List;

import com.mx.Computadoras.Entity.Computadoras;
import com.mx.Computadoras.Respuestas.Respuesta;

public interface Metodos {
	public Respuesta guardar(Computadoras compu);
	public Respuesta editar(Computadoras compu);
	public Respuesta eliminar(Computadoras compu);
	
	public Respuesta buscarId(Computadoras compu);
	public Respuesta listar();

}
