package com.mx.Ventas.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Ventas.Dao.OrdenDao;
import com.mx.Ventas.Entity.Orden;

@Service
public class ImplementacionOrden implements MOrden{

	@Autowired
	OrdenDao dao;

	@Override
	public void guardar(Orden orden) {
		if(!dao.existsById(orden.getId())) {
			dao.save(orden);
		}
		
	}

	@Override
	public void editar(Orden orden) {

		if(!dao.existsById(orden.getId())) {
			dao.save(orden);
		}
		
	}

	@Override
	public void eliminar(Orden orden) {

		dao.deleteById(orden.getId());
		
	}

	@Override
	public Orden buscar(Orden orden) {
		// TODO Auto-generated method stub
		return dao.findById(orden.getId()).orElse(null);
	}

	@Override
	public List<Orden> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}	

}
