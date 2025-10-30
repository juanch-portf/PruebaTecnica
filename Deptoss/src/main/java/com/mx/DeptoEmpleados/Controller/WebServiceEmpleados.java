package com.mx.DeptoEmpleados.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mx.DeptoEmpleados.Entity.Deptos;
import com.mx.DeptoEmpleados.Entity.Empleados;
import com.mx.DeptoEmpleados.Services.ImplementacionDeptos;
import com.mx.DeptoEmpleados.Services.ImplementacionEmpleados;

public class WebServiceEmpleados {

	@Autowired
	ImplementacionEmpleados imp;

	// http://localhost:9001/Api/Deptos/lista
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ResponseEntity<?> lista() {
		return ResponseEntity.status(HttpStatus.CREATED).body(imp.listar());
	}

	// http://localhost:9001/Api/Deptos/guardar
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public ResponseEntity<?> guardar(@RequestBody Empleados empleado) {
		imp.guardar(empleado);
		return new ResponseEntity<String>("Depto guardardo\n", HttpStatus.OK);
	}

	// http://localhost:9001/Api/Deptos/editar
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public ResponseEntity<?> editar(@RequestBody Empleados empleado) {
		imp.editar(empleado);
		return new ResponseEntity<String>("Depto editado\n", HttpStatus.OK);
	}

	// http://localhost:9001/Api/Deptos/eliminar
	@RequestMapping(value = "eliminar", method = RequestMethod.POST)
	public ResponseEntity<?> eliminar(@RequestBody Empleados empleado) {
		imp.eliminar(empleado);
		return new ResponseEntity<String>("Depto eliminado\n", HttpStatus.OK);
	}

	// http://localhost:9001/Api/Deptos/buscar
	@RequestMapping(value = "buscar", method = RequestMethod.POST)
	public ResponseEntity<?> buscar(@RequestBody Empleados empleado) {
		return ResponseEntity.status(HttpStatus.CREATED).body(imp.buscar(empleado));
	}
}
