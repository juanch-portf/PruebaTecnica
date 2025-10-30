package com.mx.Peliculas;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertFalse;

import java.sql.Date;
import java.util.List;

import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


import com.mx.Peliculas.Entity.Peliculas;
import com.mx.Peliculas.Service.Implementacion;

@RunWith(SpringRunner.class)
@SpringBootTest
class CineApplicationTests {

	@Autowired
	Implementacion imp;
	
	Peliculas newPeli= new Peliculas();
	
	@Test
	@Order(1)
	void guardar() {
		newPeli.setId(5);
		newPeli.setNombrePelicula("La llorona");
		newPeli.setActor("Mojax");
		newPeli.setGenero("Accion");
		newPeli.setFechaEstreno(Date.valueOf("2025-09-29"));
		newPeli.setClasificacion("Terror");
		newPeli.setPrecio(200.00);
		//imp.guardar(newPeli);
	}
	
	@Test
	@Order(2)
	void listar() {
		List<Peliculas> lista = imp.listar();
		System.out.println("Lista de peliculas " + lista);
		assertFalse(lista.isEmpty(), "Lista Vacia");
	}
	
	@Test
	@Order(3)
	void buscar() {
		newPeli.setId(1);
		Peliculas peliBuscar = imp.buscarId(newPeli);
		System.out.println("Se encontro la pelicula "+peliBuscar);
	}
	
	@Test
	@Order(4)
	void editar() {
		newPeli.setId(1);
		Peliculas peliBuscar = imp.buscarId(newPeli);
		peliBuscar.setNombrePelicula("Editado");
		imp.editar(peliBuscar);
		
	}
	
	@Test
	@Order(5)
	void eliminar() {
		newPeli.setId(1);
		imp.eliminar(newPeli);
		
	}

}
