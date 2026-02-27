package com.israel.docentes;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.hamcrest.Matchers.*;

import java.util.Arrays;
import java.util.Optional;

import com.israel.docentes.controllers.DocenteController;
import com.israel.docentes.model.Docente;
import com.israel.docentes.repository.DocenteRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(DocenteController.class)
public class DocenteControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DocenteRepository docenteRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    public void debeTraerTodosLosDocentes() throws Exception {
        Docente docente1 = new Docente();
        docente1.setId(1L);
        docente1.setNombre("María");
        docente1.setNumeroEmpleado("D001");
        docente1.setDepartamento("Matemáticas");

        Docente docente2 = new Docente();
        docente2.setId(2L);
        docente2.setNombre("Juan");
        docente2.setNumeroEmpleado("D002");
        docente2.setDepartamento("Física");

        when(docenteRepository.findAll()).thenReturn(Arrays.asList(docente1, docente2));

        mockMvc.perform(get("/docentes/traer-docentes")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].nombre", is("María")))
                .andExpect(jsonPath("$[1].departamento", is("Física")));
    }

    @Test
    public void debeTraerUnDocente() throws Exception {
        Docente docente = new Docente();
        docente.setId(1L);
        docente.setNombre("Carlos");
        docente.setNumeroEmpleado("D003");
        docente.setDepartamento("Historia");

        when(docenteRepository.findById(1L)).thenReturn(Optional.of(docente));

        mockMvc.perform(get("/docentes/traer-docente/1")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Carlos")))
                .andExpect(jsonPath("$.departamento", is("Historia")));
    }

    @Test
    public void debeInsertarDocente() throws Exception {
        Docente docenteNuevo = new Docente();
        docenteNuevo.setNombre("Ana");
        docenteNuevo.setNumeroEmpleado("D004");
        docenteNuevo.setDepartamento("Literatura");

        when(docenteRepository.save(any(Docente.class))).thenReturn(docenteNuevo);

        mockMvc.perform(post("/docentes/insertar-docentes")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(docenteNuevo)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre", is("Ana")))
                .andExpect(jsonPath("$.numeroEmpleado", is("D004")));
    }

    @Test
    public void debeEditarDocente() throws Exception {
        Long id = 1L;

        Docente docenteExistente = new Docente();
        docenteExistente.setId(id);
        docenteExistente.setNombre("Original");
        docenteExistente.setNumeroEmpleado("D001");
        docenteExistente.setDepartamento("Original");

        Docente docenteActualizado = new Docente();
        docenteActualizado.setNombre("Editado");
        docenteActualizado.setNumeroEmpleado("D001");
        docenteActualizado.setDepartamento("Editado");

        Docente docenteGuardado = new Docente();
        docenteGuardado.setId(id);
        docenteGuardado.setNombre("Editado");
        docenteGuardado.setNumeroEmpleado("D001");
        docenteGuardado.setDepartamento("Editado");

        when(docenteRepository.findById(id)).thenReturn(Optional.of(docenteExistente));
        when(docenteRepository.save(any(Docente.class))).thenReturn(docenteGuardado);

        mockMvc.perform(put("/docentes/editar-docentes/{id}", id)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(docenteActualizado)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.nombre", is("Editado")))
                .andExpect(jsonPath("$.departamento", is("Editado")));
    }

    @Test
    public void debeEliminarDocente() throws Exception {
        Long id = 1L;

        when(docenteRepository.existsById(id)).thenReturn(true);
        doNothing().when(docenteRepository).deleteById(id);

        mockMvc.perform(delete("/docentes/eliminar-docentes/{id}", id))
                .andExpect(status().isOk());
    }

    @Test
    public void debeRetornar404AlBuscarDocenteInexistente() throws Exception {
        when(docenteRepository.findById(999L)).thenReturn(Optional.empty());

        mockMvc.perform(get("/docentes/traer-docente/999"))
                .andExpect(status().isNotFound());
    }
}