package com.mx.Peliculas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Peliculas.Entity.Peliculas;
import com.mx.Peliculas.Service.Implementacion;

@RestController
@RequestMapping(path = "Api")
@CrossOrigin("*")
public class WebService {
	
	@Autowired
	Implementacion imp;
	
	//url: http://localhost:9000/Api/lista
	@GetMapping(path = "lista")
	public List<Peliculas> lista(){
		return imp.listar();
	}
	
	//url: http://localhost:9000/Api/guardar
	@PostMapping(path = "guardar")
	public void guardar(@RequestBody Peliculas peli){
		imp.guardar(peli);
	}
	
	//url: http://localhost:9000/Api/editar
	@PostMapping(path = "editar")
	public void editar(@RequestBody Peliculas peli){
		imp.editar(peli);
	}
	
	//url: http://localhost:9000/Api/eliminar
	@PostMapping(path = "eliminar")
	public void eliminar(@RequestBody Peliculas peli){
		imp.eliminar(peli);
	}
	
	//url: http://localhost:9000/Api/buscar
	@PostMapping(path = "buscar")
	public Peliculas buscar(@RequestBody Peliculas peli){
		return imp.buscarId(peli);
	}

}
