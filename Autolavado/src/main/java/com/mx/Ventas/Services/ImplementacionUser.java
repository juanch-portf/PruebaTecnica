package com.mx.Ventas.Services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.mx.Ventas.Dao.UserDao;
import com.mx.Ventas.Entity.Usuarios;

@Service
public class ImplementacionUser implements MetodosUsuario {

    @Autowired
    UserDao dao;

    @Autowired
    RestTemplate rest;
	
	
	public Object listaPokemon() {
		String urlPokemon = "https://pokeapi.co/api/v2/pokemon/ditto";
	
       return rest.getForObject(urlPokemon, Object.class);
	}

	@Override
	public void guardar(Usuarios usuario) {
		if(!dao.existsById(usuario.getId())) {
			dao.save(usuario);
		}
		
	}

	@Override
	public void editar(Usuarios usuario) {
		if(!dao.existsById(usuario.getId())) {
			dao.save(usuario);
		}
		
	}

	@Override
	public void eliminar(Usuarios usuario) {
		dao.deleteById(usuario.getId());
		
	}

	@Override
	public Usuarios buscar(Usuarios usuario) {
		// TODO Auto-generated method stub
		return (Usuarios) dao.findById(usuario.getId()).orElse(null);
	}

	@Override
	public List<Usuarios> listar() {
		// TODO Auto-generated method stub
		return dao.findAll();
	}

    
}
