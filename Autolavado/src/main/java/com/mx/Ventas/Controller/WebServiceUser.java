package com.mx.Ventas.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.mx.Ventas.Entity.Usuarios;
import com.mx.Ventas.Services.ImplementacionUser;

@RestController
@RequestMapping("usuarios")
@CrossOrigin("x*")
public class WebServiceUser {

    @Autowired
    ImplementacionUser imp;

    @RequestMapping(value = "lista", method = RequestMethod.GET)
    public ResponseEntity<?> lista() {
        return ResponseEntity.status(HttpStatus.CREATED).body(imp.listar());
    }

    @RequestMapping(value = "guardar", method = RequestMethod.POST)
    public ResponseEntity<?> guardar(@RequestBody Usuarios usuario) {
        imp.guardar(usuario);
		return new ResponseEntity<String>("Usuario guardado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "editar", method = RequestMethod.POST)
    public ResponseEntity<?> editar(@RequestBody Usuarios usuario) {
        imp.editar(usuario);
		return new ResponseEntity<String>("Usuario editado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "eliminar", method = RequestMethod.POST)
    public ResponseEntity<?> eliminar(@RequestBody Usuarios usuario) {
        imp.eliminar(usuario);
		return new ResponseEntity<String>("Usuario eliminado\n", HttpStatus.OK);
    }

    @RequestMapping(value = "buscar", method = RequestMethod.POST)
    public ResponseEntity<?> buscar(@RequestBody Usuarios usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(imp.buscar(usuario));
    }

    
}
