package com.israel.alumnos.controllers;

import java.util.List;
import java.util.Optional;

import com.israel.alumnos.services.AlumnoService;
import jakarta.persistence.Entity;
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

import com.israel.alumnos.model.Alumno;
import com.israel.alumnos.repository.AlumnoRepository;

@RestController
@RequestMapping("/alumnos")
@CrossOrigin(origins = "*")
public class AlumnoController {

    @Autowired
    private AlumnoService alumnoService;

    @GetMapping("/traer-alumnos")
    public List<Alumno> TraerAlumnos() {
        return alumnoService.obtenerTodos();
    }

    @GetMapping("/traer-alumno/{id}")
    public ResponseEntity<Alumno> TraerUnAlumno(@PathVariable long id) {
        Optional<Alumno> alumno =alumnoService.obtenerPorID(id);
return  alumno.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-alumnos")
    @SuppressWarnings("null")
    public Alumno insertarAlumno(@RequestBody Alumno alumno) {
        return alumnoService.guardarAlumno(alumno);
    }
    @PutMapping("/editar-alumnos/{id}")
    public ResponseEntity<Alumno> actualizarAlumno(@PathVariable long id, @RequestBody Alumno alumno) {
Optional<Alumno>actualizado = alumnoService.actualizarAlumno(id,alumno);
return actualizado.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

@DeleteMapping("/eliminar-alumno/{id}")
    public ResponseEntity<Void> eliminarAlumno(@PathVariable Long id){
        alumnoService.eliminarAlumno(id);
        return ResponseEntity.ok().build();
}

}
