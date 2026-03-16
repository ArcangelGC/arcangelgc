package com.israel.alumnos.controllers;

import com.israel.alumnos.model.Inscripcion;
import com.israel.alumnos.services.InscripcionService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/inscripciones")
@CrossOrigin(origins = "*")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    @GetMapping("/traer-inscripciones")
    public List<Inscripcion> traerInscripciones() {
        return inscripcionService.obtenerTodas();
    }

    @PostMapping("/insertar-inscripcion")
    public Inscripcion insertarInscripcion(@RequestBody Inscripcion inscripcion) {
        return inscripcionService.guardarInscripcion(inscripcion);
    }

    @DeleteMapping("/eliminar-inscripcion/{id}")
    public ResponseEntity<Void> eliminarInscripcion(@PathVariable Long id) {
        inscripcionService.eliminarInscripcion(id);
        return ResponseEntity.ok().build();
    }
}