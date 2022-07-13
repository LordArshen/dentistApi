package com.dentist.service;

import com.dentist.model.dto.TurnoDTO;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface ITurnoService {

    /* ──────────────
       MÉTODOS CRUD
     ────────────── */

    // GUARDAR
    TurnoDTO saveAndFlush(TurnoDTO turnoDTO);

    // BUSCAR TODOS
    List<TurnoDTO> findAll();

    // BUSCAR POR ID
    TurnoDTO findById(Integer id);

    // ELIMINAR POR ID
    void deleteById(Integer id);

    // MODIFICAR POR ID
    void update(TurnoDTO turnoDto);

}
