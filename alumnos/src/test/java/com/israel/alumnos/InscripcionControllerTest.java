package com.israel.alumnos;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.israel.alumnos.controllers.InscripcionController;
import com.israel.alumnos.model.Inscripcion;
import com.israel.alumnos.services.InscripcionService;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;

@WebMvcTest(InscripcionController.class)
public class InscripcionControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private InscripcionService inscripcionService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void debeTraerInscripciones() throws Exception {

        Inscripcion inscripcion = new Inscripcion();
        inscripcion.setId(1L);
        inscripcion.setAlumnoId(1L);
        inscripcion.setMateriaId(2L);

        when(inscripcionService.obtenerTodas())
                .thenReturn(Arrays.asList(inscripcion));

        mockMvc.perform(get("/inscripciones/traer-inscripciones"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].alumnoId").value(1))
                .andExpect(jsonPath("$[0].materiaId").value(2));
    }

    @Test
    public void debeInsertarInscripcion() throws Exception {

        Inscripcion nueva = new Inscripcion();
        nueva.setAlumnoId(3L);
        nueva.setMateriaId(4L);

        when(inscripcionService.guardarInscripcion(any(Inscripcion.class)))
                .thenReturn(nueva);

        mockMvc.perform(post("/inscripciones/insertar-inscripcion")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(nueva)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.alumnoId").value(3))
                .andExpect(jsonPath("$.materiaId").value(4));
    }

    @Test
    public void debeEliminarInscripcion() throws Exception {

        Long id = 1L;

        doNothing().when(inscripcionService).eliminarInscripcion(id);

        mockMvc.perform(delete("/inscripciones/eliminar-inscripcion/{id}", id))
                .andExpect(status().isOk());
    }
}