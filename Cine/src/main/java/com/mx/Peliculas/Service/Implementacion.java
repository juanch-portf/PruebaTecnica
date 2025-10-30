package com.mx.Peliculas.Service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Peliculas.Dao.IMetodos;
import com.mx.Peliculas.Entity.Peliculas;

@Service
public class Implementacion implements Metodos {

	@Autowired
	IMetodos dao;
	
	@Override
	public void guardar(Peliculas peli) {
		dao.save(peli);
		
	}

	@Override
	public void editar(Peliculas peli) {
		dao.save(peli);
		
	}

	@Override
	public void eliminar(Peliculas peli) {
		dao.deleteById(peli.getId());
		
	}

	@Override
	public Peliculas buscarId(Peliculas peli) {
		return dao.findById(peli.getId()).orElse(null);
	}

	@Override
	public List<Peliculas> listar() {
		// TODO Auto-generated method stub
		return (List<Peliculas>) dao.findAll();
	}

	@Override
	public List<Peliculas> listar(Peliculas peli) {
		
		List<Peliculas> listaGenero = new ArrayList<>();
		
		for(Peliculas p : listar()) {
			if(p.getGenero().equalsIgnoreCase(peli.getGenero())) {
				listaGenero.add(p);
			}
		}
		
		return listaGenero;
	}

	
}
