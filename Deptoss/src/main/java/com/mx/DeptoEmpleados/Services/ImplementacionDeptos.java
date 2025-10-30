package com.mx.DeptoEmpleados.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.DeptoEmpleados.Dao.DeptosDao;
import com.mx.DeptoEmpleados.Entity.Deptos;

@Service
public class ImplementacionDeptos implements DMetodos{

	@Autowired
	DeptosDao dao;
	
	
	@Override
	public void guardar(Deptos depto) {
		dao.save(depto);
		
	}

	@Override
	public void editar(Deptos depto) {
		dao.save(depto);
		
	}

	@Override
	public void eliminar(Deptos depto) {
		dao.delete(depto);
		
	}

	@Override
	public List<Deptos> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Deptos buscar(Deptos depto) {
		// TODO Auto-generated method stub
		return dao.findById(depto.getId()).orElse(null);
	}

	
}
