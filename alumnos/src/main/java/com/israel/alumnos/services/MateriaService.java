package com.israel.alumnos.services;

import com.israel.alumnos.model.Materia;
import com.israel.alumnos.repository.MateriaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MateriaService {

    @Autowired
    private MateriaRepository materiaRepository;

    public List<Materia> obtenerTodas() {
        return materiaRepository.findAll();
    }

    public Optional<Materia> obtenerPorId(Long id) {
        return materiaRepository.findById(id);
    }

    public Materia guardarMateria(Materia materia) {
        return materiaRepository.save(materia);
    }

    public void eliminarMateria(Long id) {
        materiaRepository.deleteById(id);
    }
}