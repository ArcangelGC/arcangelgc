package com.israel.alumnos.controllers;

import com.israel.alumnos.model.Materia;
import com.israel.alumnos.services.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/materias")
@CrossOrigin(origins = "*")
public class MateriaController {

    @Autowired
    private MateriaService materiaService;

    @GetMapping("/traer-materias")
    public List<Materia> traerMaterias() {
        return materiaService.obtenerTodas();
    }

    @GetMapping("/traer-materia/{id}")
    public ResponseEntity<Materia> traerUnaMateria(@PathVariable Long id) {

        Optional<Materia> materia = materiaService.obtenerPorId(id);

        return materia.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-materia")
    public Materia insertarMateria(@RequestBody Materia materia) {
        return materiaService.guardarMateria(materia);
    }

    @DeleteMapping("/eliminar-materia/{id}")
    public ResponseEntity<Void> eliminarMateria(@PathVariable Long id) {
        materiaService.eliminarMateria(id);
        return ResponseEntity.ok().build();
    }
}