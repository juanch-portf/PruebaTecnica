package com.mx.DeptoEmpleados.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.DeptoEmpleados.Dao.EmpleadosDao;
import com.mx.DeptoEmpleados.Entity.Empleados;

@Service
public class ImplementacionEmpleados implements EMetodos{

	@Autowired
	EmpleadosDao dao;
	
	@Override
	public void guardar(Empleados empleado) {
		dao.save(empleado);
		
	}

	@Override
	public void editar(Empleados empleado) {
		dao.save(empleado);
		
	}

	@Override
	public void eliminar(Empleados empleado) {
		dao.delete(empleado);
		
	}

	@Override
	public List<Empleados> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

	@Override
	public Empleados buscar(Empleados empleado) {
		// TODO Auto-generated method stub
		return dao.findById(empleado.getId()).orElse(null);
	}

}
