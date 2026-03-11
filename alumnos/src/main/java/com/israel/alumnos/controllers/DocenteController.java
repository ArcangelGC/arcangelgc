package com.israel.alumnos.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.israel.alumnos.model.Docente;
import com.israel.alumnos.services.DocenteService;

@RestController
@RequestMapping("/docentes")
@CrossOrigin(origins = "*")
public class DocenteController {

    @Autowired
    private DocenteService docenteService;

    @GetMapping("/traer-docentes")
    public List<Docente> traerDocentes() {
        return docenteService.obtenerTodos();
    }

    @GetMapping("/traer-docente/{id}")
    public ResponseEntity<Docente> traerUnDocente(@PathVariable Long id) {
        Optional<Docente> docente = docenteService.obtenerPorID(id);
        return docente.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-docentes")
    public Docente insertarDocente(@RequestBody Docente docente) {
        return docenteService.guardarDocente(docente);
    }

    @PutMapping("/editar-docentes/{id}")
    public ResponseEntity<Docente> editarDocente(@PathVariable Long id, @RequestBody Docente docente) {
        Optional<Docente> actualizado = docenteService.actualizarDocente(id, docente);
        return actualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar-docentes/{id}")
    public ResponseEntity<Void> eliminarDocente(@PathVariable Long id) {
        docenteService.eliminarDocente(id);
        return ResponseEntity.ok().build();
    }
}