package com.mx.Ventas.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Ventas.Dao.AutosDao;
import com.mx.Ventas.Entity.Autos;

@Service
public class ImplementacionAutos implements MetodosAuto{

	@Autowired
	AutosDao dao;

	@Override
	public void guardar(Autos auto) {
		if(!dao.existsById(auto.getId())) {
			dao.save(auto);
		}
		
	}

	@Override
	public void editar(Autos auto) {
		if(!dao.existsById(auto.getId())) {
			dao.save(auto);
		}
		
	}

	@Override
	public void eliminar(Autos auto) {
		dao.deleteById(auto.getId());
		
	}

	@Override
	public Autos buscar(Autos auto) {
		// TODO Auto-generated method stub
		return dao.findById(auto.getId()).orElse(null);
	}

	@Override
	public List<Autos> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

}
