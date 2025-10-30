package com.mx.Empleados.Controller;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Empleados.Entity.Empleados;
import com.mx.Empleados.Services.Implementacion;

@RestController
@RequestMapping(path = "Nominas")
@CrossOrigin("*")
public class WebService {

	@Autowired
	Implementacion imp;

	// url: http://localhost:9001/Nominas/lista
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ResponseEntity<?> lista() {

		if (imp.listar().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(imp.listar());
		}
	}

	// url: http://localhost:9001/Nominas/guardar
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public ResponseEntity<?> guardar(@RequestBody Empleados empleado) {
		imp.guardar(empleado);
		String res = "Se guardo el empleado";
		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

	// url: http://localhost:9001/Nominas/editar
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public ResponseEntity<?> editar(@RequestBody Empleados empleado) {
		imp.editar(empleado);
		String res = "Se edito el empleado";
		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

	// url: http://localhost:9001/Nominas/eliminar
	@RequestMapping(value = "eliminar", method = RequestMethod.POST)
	public ResponseEntity<?> eliminar(@RequestBody Empleados empleado) {
		imp.eliminar(empleado);
		String res = "Se elimino el empleado";
		return new ResponseEntity<String>(res, HttpStatus.OK);

	}

	// url: http://localhost:9001/Nominas/buscar
	@RequestMapping(value = "buscar", method = RequestMethod.POST)
	public ResponseEntity<?> buscar(@RequestBody Empleados empleado) {
		Empleados empleBuscar = imp.buscarId(empleado);

		if (empleBuscar == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(empleBuscar);
		}

	}

	// url: http://localhost:9001/Nominas/buscarNombre
	@RequestMapping(value = "buscarNombre", method = RequestMethod.POST)
	public ResponseEntity<?> buscarNombre(@RequestBody Empleados empleado) {
		List<Empleados> empleBuscar = imp.buscarNombre(empleado);

		if (empleBuscar == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(empleBuscar);
		}

	}

	// url: http://localhost:9001/Nominas/agruparDepto
	@RequestMapping(value = "agruparDepto", method = RequestMethod.GET)
	public Map<String, List<Empleados>> agruparDepto() {
		
		return imp.groupByDepto();

	}
}
