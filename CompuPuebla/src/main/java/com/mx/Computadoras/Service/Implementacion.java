package com.mx.Computadoras.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Computadoras.Dao.CompuDao;
import com.mx.Computadoras.Entity.Computadoras;
import com.mx.Computadoras.Respuestas.Respuesta;

@Service
public class Implementacion implements Metodos {

	@Autowired
	CompuDao dao;

	@Override
	public Respuesta guardar(Computadoras compu) {

		if (dao.existsById(compu.getId())) {
			return new Respuesta("El ID ya esta registrado", false, null);
		} else {

			dao.guardar(compu.getId(), compu.getMarca(), compu.getModelo(), compu.getColor(), compu.getCapacidad(),
					compu.getFechaVenta(), compu.getCantidad(), compu.getPrecio());
			return new Respuesta("Se registro nueva computadora", true, compu);
		}
	}

	@Override
	public Respuesta editar(Computadoras compu) {
		if (dao.existsById(compu.getId())) {

			dao.editar(compu.getMarca(), compu.getModelo(), compu.getColor(), compu.getCapacidad(),
					compu.getFechaVenta(), compu.getCantidad(), compu.getPrecio(), compu.getId());
			return new Respuesta("Se edito la computadora", true, compu);
		} else {

			return new Respuesta("El ID ya esta registrado", false, null);
		}

	}

	@Override
	public Respuesta eliminar(Computadoras compu) {
		if (dao.existsById(compu.getId())) {

			dao.eliminar(compu.getId());
			return new Respuesta("Se elimino la computadora", true, compu);
		} else {

			return new Respuesta("No existe el ID a eliminar", false, null);
		}
	}

	@Override
	public Respuesta buscarId(Computadoras compu) {
		Computadoras compuBuscar = dao.buscarCompu(compu.getId());

		if (compuBuscar != null) {
			return new Respuesta("Se encontro la computadora", true, compuBuscar);
		} else {
			return new Respuesta("No se encontro la computadora", false, null);
		}

	}

	@Override
	public Respuesta listar() {
		List<Computadoras> lista = dao.listaCompu();

		if (!lista.isEmpty()) {
			return new Respuesta("Lista no vacia", true, lista);
		} else {
			return new Respuesta("Lista vacia", false, null);
		}
	}

}
