package com.israel.docentes.controllers;

import java.util.List;

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

import com.israel.docentes.model.Docente;
import com.israel.docentes.repository.DocenteRepository;

@RestController
@RequestMapping("/docentes")
@CrossOrigin(origins = "*")
public class DocenteController {

    @Autowired //Inyecta una instancia
    private DocenteRepository docenteRepository;

    @GetMapping("/traer-docentes") //Atiende peticiones GET
    public List<Docente> traerDocentes() {
        return docenteRepository.findAll();
    }

    @GetMapping("/traer-docente/{id}")
    public ResponseEntity<Docente> traerUnDocente(@PathVariable Long id) {
        return docenteRepository.findById(id)
                .map(ResponseEntity::ok) //Convierte el json en un objeto
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping("/insertar-docentes") //Atiende peticiones post
    public Docente insertarDocente(@RequestBody Docente docente) {
        return docenteRepository.save(docente);
    }

    @PutMapping("/editar-docentes/{id}") //Peticiones put
    public ResponseEntity<Docente> editarDocente(@PathVariable Long id, @RequestBody Docente docente) {
        return docenteRepository.findById(id)
                .map(docenteExistente -> {
                    docenteExistente.setNumeroEmpleado(docente.getNumeroEmpleado());
                    docenteExistente.setNombre(docente.getNombre());
                    docenteExistente.setApellido(docente.getApellido());
                    docenteExistente.setTelefono(docente.getTelefono());
                    docenteExistente.setEmail(docente.getEmail());
                    docenteExistente.setDepartamento(docente.getDepartamento());
                    docenteExistente.setImagenURL(docente.getImagenURL());
                    Docente actualizado = docenteRepository.save(docenteExistente);
                    return ResponseEntity.ok(actualizado);
                })
                .orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/eliminar-docentes/{id}") //Atiende peticiones de delate
    public ResponseEntity<Void> eliminarDocente(@PathVariable Long id) {
        if (!docenteRepository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        docenteRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}