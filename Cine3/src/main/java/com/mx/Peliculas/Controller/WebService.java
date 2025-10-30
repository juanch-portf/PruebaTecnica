package com.mx.Peliculas.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Peliculas.Entity.Peliculas;
import com.mx.Peliculas.Service.Implementacion;

@RestController
@RequestMapping(path = "Api")
@CrossOrigin("*")
public class WebService {

	@Autowired
	Implementacion imp;

	// url: http://localhost:9000/Api/lista
	@RequestMapping(value = "lista", method = RequestMethod.GET)
	public ResponseEntity<?> lista() {

		if (imp.listar().isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(imp.listar());
		}

	}

	// url: http://localhost:9000/Api/guardar
	@RequestMapping(value = "guardar", method = RequestMethod.POST)
	public ResponseEntity<?> guardar(@RequestBody Peliculas peli) {
		imp.guardar(peli);
		String respuesta = "Se guardo la pelicula";
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	// url: http://localhost:9000/Api/editar
	@RequestMapping(value = "editar", method = RequestMethod.POST)
	public ResponseEntity<?> editar(@RequestBody Peliculas peli) {
		imp.editar(peli);
		String respuesta = "Se edito la pelicula";
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	// url: http://localhost:9000/Api/eliminar
	@RequestMapping(value = "eliminar", method = RequestMethod.POST)
	public ResponseEntity<?> eliminar(@RequestBody Peliculas peli) {
		imp.eliminar(peli);
		String respuesta = "Se elimino la pelicula";
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	// url: http://localhost:9000/Api/eliminarNombre
	@RequestMapping(value = "eliminarNombre", method = RequestMethod.POST)
	public ResponseEntity<?> eliminarNombre(@RequestBody Peliculas peli) {
		imp.eliminarNombre(peli.getNombrePelicula());
		String respuesta = "Se elimino la pelicula";
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);
	}

	// url: http://localhost:9000/Api/buscar
	@RequestMapping(value = "buscar", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@RequestBody Peliculas peli) {
		Peliculas peliBuscar = imp.buscarId(peli);

		if (peliBuscar == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(peliBuscar);
		}

	}

	// url: http://localhost:9000/Api/buscarNombre
	@RequestMapping(value = "buscarNombre", method = RequestMethod.GET)
	public ResponseEntity<?> buscarNombre(@RequestBody Peliculas peli) {
		return ResponseEntity.status(HttpStatus.CREATED).body(peli.getNombrePelicula());
	}

	@RequestMapping(value = "buscar/{String}", method = RequestMethod.GET)
	public ResponseEntity<?> buscarNombre(@PathVariable("nombre") String nombre) {
		Peliculas peliBuscar = imp.buscarNombre(nombre);

		if (peliBuscar == null) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
		} else {
			return ResponseEntity.status(HttpStatus.CREATED).body(peliBuscar);
		}

	}

	@RequestMapping(value = "eliminar/{String}", method = RequestMethod.GET)
	public ResponseEntity<?> eliminarNombre(@PathVariable("nombre") String nombre) {
		imp.eliminarNombre(nombre);
		String respuesta = "Se elimino la computadora";
		return new ResponseEntity<String>(respuesta, HttpStatus.OK);

	}

}
