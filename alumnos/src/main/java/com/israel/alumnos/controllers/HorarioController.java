package com.israel.alumnos.controllers;

import com.israel.alumnos.model.Horario;
import com.israel.alumnos.services.HorarioService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/horarios")
public class HorarioController {

    private final HorarioService horarioService;

    public HorarioController(HorarioService horarioService) {
        this.horarioService = horarioService;
    }

    @GetMapping
    public List<Horario> obtenerTodos() {
        return horarioService.obtenerTodos();
    }

    @PostMapping
    public Horario crear(@RequestBody Horario horario) {
        return horarioService.guardar(horario);
    }

    @GetMapping("/{id}")
    public Horario obtenerPorId(@PathVariable Long id) {
        return horarioService.obtenerPorId(id);
    }

    @DeleteMapping("/{id}")
    public void eliminar(@PathVariable Long id) {
        horarioService.eliminar(id);
    }
}