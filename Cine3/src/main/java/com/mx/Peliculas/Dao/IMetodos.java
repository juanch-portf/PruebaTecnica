package com.mx.Peliculas.Dao;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import org.yaml.snakeyaml.events.Event.ID;

import com.mx.Peliculas.Entity.Peliculas;

@Repository
public interface IMetodos extends CrudRepository<Peliculas, Integer> {

	public Peliculas findByNombrePelicula(String nombrePelicula);
	public Peliculas findAllByGenero(String genero);
	public void deleteByNombrePelicula(String nombrePelicula);
	public void deleteAllByGenero(String genero);
}
