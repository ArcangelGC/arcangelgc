package com.israel.alumnos.services;

import com.israel.alumnos.model.Calificacion;
import com.israel.alumnos.repository.CalificacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CalificacionService {

    private final CalificacionRepository calificacionRepository;

    public CalificacionService(CalificacionRepository calificacionRepository) {
        this.calificacionRepository = calificacionRepository;
    }

    public List<Calificacion> obtenerTodas() {
        return calificacionRepository.findAll();
    }

    public Calificacion guardar(Calificacion calificacion) {
        return calificacionRepository.save(calificacion);
    }

    public Calificacion obtenerPorId(Long id) {
        return calificacionRepository.findById(id).orElse(null);
    }

    public void eliminar(Long id) {
        calificacionRepository.deleteById(id);
    }
}