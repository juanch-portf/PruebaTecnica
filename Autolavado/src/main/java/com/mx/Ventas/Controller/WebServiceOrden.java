package com.mx.Ventas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Ventas.Entity.Orden;
import com.mx.Ventas.Services.ImplementacionOrden;

@RestController
@RequestMapping("orden")
@CrossOrigin("x*")
public class WebServiceOrden {
	
	@Autowired
    ImplementacionOrden imp;

    @RequestMapping(value = "lista", method = RequestMethod.GET)
    public ResponseEntity<?> lista() {
        return ResponseEntity.status(HttpStatus.CREATED).body(imp.listar());
    }

    @RequestMapping(value = "guardar", method = RequestMethod.POST)
    public ResponseEntity<?> guardar(@RequestBody Orden orden) {
        imp.guardar(orden);
		return new ResponseEntity<String>("Orden guardado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "editar", method = RequestMethod.POST)
    public ResponseEntity<?> editar(@RequestBody Orden orden) {
        imp.editar(orden);
		return new ResponseEntity<String>("Orden editado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "eliminar", method = RequestMethod.POST)
    public ResponseEntity<?> eliminar(@RequestBody Orden orden) {
        imp.eliminar(orden);
		return new ResponseEntity<String>("Orden eliminada\n", HttpStatus.OK);
    }

    @RequestMapping(value = "buscar", method = RequestMethod.POST)
    public ResponseEntity<?> buscar(@RequestBody Orden orden) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imp.buscar(orden));
    }

}
