package com.israel.alumnos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.israel.alumnos.controllers.MateriaController;
import com.israel.alumnos.model.Materia;
import com.israel.alumnos.services.MateriaService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

@WebMvcTest(MateriaController.class)
public class MateriaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private MateriaService materiaService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void debeTraerMaterias() throws Exception {

        Materia materia = new Materia();
        materia.setId(1L);
        materia.setNombre("Programación");
        materia.setCreditos(5);

        when(materiaService.obtenerTodas())
                .thenReturn(Arrays.asList(materia));

        mockMvc.perform(get("/materias/traer-materias"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nombre").value("Programación"))
                .andExpect(jsonPath("$[0].creditos").value(5));
    }

    @Test
    public void debeTraerUnaMateria() throws Exception {

        Materia materia = new Materia();
        materia.setId(1L);
        materia.setNombre("Base de Datos");
        materia.setCreditos(4);

        when(materiaService.obtenerPorId(1L))
                .thenReturn(Optional.of(materia));

        mockMvc.perform(get("/materias/traer-materia/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Base de Datos"))
                .andExpect(jsonPath("$.creditos").value(4));
    }

    @Test
    public void debeInsertarMateria() throws Exception {

        Materia materiaNueva = new Materia();
        materiaNueva.setNombre("Redes");
        materiaNueva.setCreditos(3);

        when(materiaService.guardarMateria(any(Materia.class)))
                .thenReturn(materiaNueva);

        mockMvc.perform(post("/materias/insertar-materia")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(materiaNueva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value("Redes"))
                .andExpect(jsonPath("$.creditos").value(3));
    }

    @Test
    public void debeEliminarMateria() throws Exception {

        Long id = 1L;

        doNothing().when(materiaService).eliminarMateria(id);

        mockMvc.perform(delete("/materias/eliminar-materia/{id}", id))
                .andExpect(status().isOk());
    }
}