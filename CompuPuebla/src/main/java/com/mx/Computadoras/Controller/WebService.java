package com.mx.Computadoras.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Computadoras.Entity.Computadoras;
import com.mx.Computadoras.Respuestas.Respuesta;
import com.mx.Computadoras.Service.Implementacion;

@RestController
@RequestMapping(path = "Api/webService")
@CrossOrigin("*")
public class WebService {

	@Autowired
	Implementacion imp;
	
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public Respuesta lista() {
		return imp.listar();
	}
	
	@RequestMapping(value = "guardar", method = RequestMethod.GET)
	public Respuesta guardar(@RequestBody Computadoras compu) {
		return imp.guardar(compu);
	}
	
	@RequestMapping(value = "editar", method = RequestMethod.GET)
	public Respuesta editar(@RequestBody Computadoras compu) {
		return imp.editar(compu);
	}
	
	@RequestMapping(value = "eliminar", method = RequestMethod.GET)
	public Respuesta eliminar(@RequestBody Computadoras compu) {
		return imp.eliminar(compu);
	}
	
	@RequestMapping(value = "buscar", method = RequestMethod.GET)
	public Respuesta buscar(@RequestBody Computadoras compu) {
		return imp.buscarId(compu);
	}
	
}
