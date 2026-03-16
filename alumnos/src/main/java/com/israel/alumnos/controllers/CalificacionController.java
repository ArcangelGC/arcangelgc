package com.israel.alumnos.controllers;

import com.israel.alumnos.model.Calificacion;
import com.israel.alumnos.services.CalificacionService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/calificaciones")
public class CalificacionController {

    private final CalificacionService calificacionService;

    public CalificacionController(CalificacionService calificacionService) {
        this.calificacionService = calificacionService;
    }

    @GetMapping
    public List<Calificacion> obtenerTodas() {
        return calificacionService.obtenerTodas();
    }

    @PostMapping
    public Calificacion crear(@RequestBody Calificacion calificacion) {
        return calificacionService.guardar(calificacion);
    }

    @GetMapping("/{id}")
    public Calificacion obtenerPorId(@PathVariable Long id) {
        return calificacionService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        calificacionService.eliminar(id);
    }
}