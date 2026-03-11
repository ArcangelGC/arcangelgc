package com.israel.alumnos.services;

import com.israel.alumnos.model.Docente;
import com.israel.alumnos.repository.DocenteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DocenteService {

    @Autowired
    private DocenteRepository docenteRepository;

    public List<Docente> obtenerTodos() {
        return docenteRepository.findAll();
    }

    public Optional<Docente> obtenerPorID(Long id) {
        return docenteRepository.findById(id);
    }

    public Docente guardarDocente(Docente docente) {
        return docenteRepository.save(docente);
    }

    public Optional<Docente> actualizarDocente(Long id, Docente docenteDetalles) {
        return docenteRepository.findById(id).map(docenteExistente -> {
            docenteExistente.setNumeroEmpleado(docenteDetalles.getNumeroEmpleado());
            docenteExistente.setNombre(docenteDetalles.getNombre());
            docenteExistente.setApellido(docenteDetalles.getApellido());
            docenteExistente.setTelefono(docenteDetalles.getTelefono());
            docenteExistente.setEmail(docenteDetalles.getEmail());
            docenteExistente.setDepartamento(docenteDetalles.getDepartamento());
            docenteExistente.setImagenURL(docenteDetalles.getImagenURL());
            return docenteRepository.save(docenteExistente);
        });
    }

    public void eliminarDocente(Long id) {
        docenteRepository.deleteById(id);
    }
}