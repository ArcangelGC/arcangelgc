package com.israel.alumnos.repository;

import com.israel.alumnos.model.Docente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DocenteRepository extends JpaRepository<Docente, Long> {

}