package com.mx.Ventas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.mx.Ventas.Entity.Autos;
import com.mx.Ventas.Services.ImplementacionAutos;

@RestController
@RequestMapping(path = "autos")
@CrossOrigin("*")
public class WebServiceAutos {
	
	@Autowired
    ImplementacionAutos imp;

    @RequestMapping(value = "lista", method = RequestMethod.GET)
    public ResponseEntity<?> lista() {
        return ResponseEntity.status(HttpStatus.CREATED).body(imp.listar());
    }

    @RequestMapping(value = "guardar", method = RequestMethod.POST)
    public ResponseEntity<?> guardar(@RequestBody Autos auto) {
        imp.guardar(auto);
		return new ResponseEntity<String>("Automovil guardado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "editar", method = RequestMethod.POST)
    public ResponseEntity<?> editar(@RequestBody Autos auto) {
        imp.editar(auto);
		return new ResponseEntity<String>("Automovil editado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "eliminar", method = RequestMethod.POST)
    public ResponseEntity<?> eliminar(@RequestBody Autos auto) {
        imp.eliminar(auto);
		return new ResponseEntity<String>("Automovil eliminado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "buscar", method = RequestMethod.POST)
    public ResponseEntity<?> buscar(@RequestBody Autos auto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imp.buscar(auto));
    }

}
