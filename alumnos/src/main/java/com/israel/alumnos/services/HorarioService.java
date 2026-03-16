package com.israel.alumnos.services;

import com.israel.alumnos.model.Horario;
import com.israel.alumnos.repository.HorarioRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class HorarioService {

    private final HorarioRepository horarioRepository;

    public HorarioService(HorarioRepository horarioRepository) {
        this.horarioRepository = horarioRepository;
    }

    public List<Horario> obtenerTodos() {
        return horarioRepository.findAll();
    }

    public Horario guardar(Horario horario) {
        return horarioRepository.save(horario);
    }

    public Horario obtenerPorId(Long id) {
        return horarioRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        horarioRepository.deleteById(id);
    }
}