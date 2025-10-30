package com.mx.Empleados.Services;

import java.time.LocalDate;
import java.time.Period;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collector;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mx.Empleados.Dao.EmpleadosDao;
import com.mx.Empleados.Entity.Empleados;

@Service
public class Implementacion implements Metodos {

	@Autowired
	EmpleadosDao dao;

	public void guardar(Empleados e) {
        // Calcular edad
        int edad = Period.between(e.getFechaNacimiento().toLocalDate(), LocalDate.now()).getYears();
        if (edad < 22) {
            throw new RuntimeException("El empleado debe tener más de 22 años.");
        }

        // Validar duplicado
        List<Empleados> existentes = dao.listaEmpleados();
        boolean existe = existentes.stream()
            .anyMatch(emp -> emp.getNombre().equalsIgnoreCase(e.getNombre()) 
                          && emp.getApp().equalsIgnoreCase(e.getApp()));
        if (existe) {
            throw new RuntimeException("Empleado ya registrado.");
        }

        // Calcular totalPago
        double pago = (e.getSueldo() / e.getDiasLaborales()) * (e.getDiasLaborales() - e.getFaltas());
        e.setTotalPago(pago);

        // Mandar al DAO con query nativo
        dao.guardar(e.getId(), e.getNombre(), e.getApp(), e.getFechaNacimiento(), edad,
                e.getSexo(), e.getDepto(), e.getSueldo(), e.getTipoContrato(),
                e.getDiasLaborales(), e.getFaltas(), e.getTotalPago());
    }


	@Override
	public void editar(Empleados empleado) {
		if (dao.existsById(empleado.getId())) {
			dao.editar(empleado.getNombre(), empleado.getApp(), empleado.getFechaNacimiento(), empleado.getEdad(), empleado.getSexo(),
					empleado.getDepto(), empleado.getSueldo(), empleado.getTipoContrato(), empleado.getDiasLaborales(),
					empleado.getFaltas(), empleado.getTotalPago(), empleado.getId());
		}

	}

	@Override
	public void eliminar(Empleados empleado) {
		if (dao.existsById(empleado.getId())) {
			dao.eliminar(empleado.getId());
		}

	}

	@Override
	public List<Empleados> listar() {
		// TODO Auto-generated method stub
		return dao.listaEmpleados();
	}

	@Override
	public Empleados buscarId(Empleados empleado) {
		Empleados empleBuscar = dao.buscarEmpleado(empleado.getId());

		if (empleBuscar != null) {
			return empleBuscar;
		}
		return null;
	}

	public List<Empleados> buscarNombre(Empleados empleado) {
		return listar().stream().filter(a -> a.getNombre().equalsIgnoreCase(empleado.getNombre()))
				.collect(Collectors.toList());

	}
	
	public Map<String, List<Empleados>> groupByDepto() {
		return listar().stream().collect(Collectors.groupingBy(Empleados :: getDepto));
	}

}
