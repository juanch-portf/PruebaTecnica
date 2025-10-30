package com.mx.Clientes.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Clientes.Entity.Clientes;
import com.mx.Clientes.Services.Implementacion;

@RestController
@RequestMapping(path = "Api")
@CrossOrigin("*")
public class WebService {

	@Autowired
	Implementacion imp;
	
	//url: http://localhost:9000/Api/lista
	@GetMapping(path = "lista")
	public List<Clientes> lista(){
		return imp.listar();
	}
	
	//url: http://localhost:9000/Api/guardar
	@PostMapping(path = "guardar")
	public void guardar(@RequestBody Clientes cliente) {
		imp.guardar(cliente);
	}
	
	//url: http://localhost:9000/Api/editar
	@PostMapping(path = "editar")
	public void editar(@RequestBody Clientes cliente) {
		imp.editar(cliente);
	}
	
	//url: http://localhost:9000/Api/eliminar
	@PostMapping(path = "eliminar")
	public void eliminar(@RequestBody Clientes cliente) {
		imp.eliminar(cliente);
	}
	
	//url: http://localhost:9000/Api/buscar
	@PostMapping(path = "buscar")
	public Clientes buscar(@RequestBody Clientes cliente) {
		return imp.buscarId(cliente);
		
	}
	
}
