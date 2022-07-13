package com.dentist.service;

import com.dentist.model.dto.PacienteDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface IPacienteService {

    /* ──────────────
       MÉTODOS CRUD
     ────────────── */

    // GUARDAR
    PacienteDTO saveAndFlush(PacienteDTO pacienteDTO);

    // BUSCAR TODOS
    List<PacienteDTO> findAll();

    // BUSCAR POR ID
    PacienteDTO findById(Integer id);

    // ELIMINAR POR ID
    void deleteById(Integer id);

    // MODIFICAR POR ID
    void update(PacienteDTO pacienteDTO);

}
