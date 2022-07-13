package com.dentist.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dentist.exception.NotFoundIdException;
import com.dentist.model.dto.PacienteDTO;
import com.dentist.model.entity.Paciente;
import com.dentist.repository.IPacienteRepository;
import com.dentist.service.IPacienteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PacienteService implements IPacienteService {

    @Autowired
    IPacienteRepository pacienteRepository;

    @Autowired
    ObjectMapper mapper;

   /* ──────────────
     MÉTODOS CRUD
   ────────────── */

    // GUARDAR
    public PacienteDTO saveAndFlush(PacienteDTO pacienteDTO) {
        Paciente pacienteAGuardar = mapper.convertValue(pacienteDTO, Paciente.class);
        pacienteRepository.saveAndFlush(pacienteAGuardar);
        return mapper.convertValue(pacienteAGuardar, PacienteDTO.class);
    }
    /* ----------------------------------------------------------------------------- */

    // BUSCAR TODOS
    public List<PacienteDTO> findAll() {
        List<PacienteDTO> listaPacientesDTO = new ArrayList<>();
        List<Paciente> listaPacientes = pacienteRepository.findAll();
        for (Paciente paciente : listaPacientes) {
            listaPacientesDTO.add(mapper.convertValue(paciente, PacienteDTO.class));
        }
        return listaPacientesDTO;
    }
    /* ----------------------------------------------------------------------------- */

    // BUSCAR POR ID
    public PacienteDTO findById(Integer id){
        PacienteDTO pacienteEncontrado = mapper.convertValue(pacienteRepository.findById(id).orElseThrow(() -> new NotFoundIdException("El Paciente con ID " + id + " no fue encontrado")), PacienteDTO.class);
        return pacienteEncontrado;
    }
    /* ----------------------------------------------------------------------------- */

    // ELIMINAR POR ID
    public void deleteById(Integer id) {
        pacienteRepository.findById(id).orElseThrow(() -> new NotFoundIdException("El Paciente con ID " + id + " no fue encontrado y no pudo eliminarse"));
        pacienteRepository.deleteById(id);
    }
    /* ----------------------------------------------------------------------------- */

    // MODIFICAR POR ID
    public void update(PacienteDTO pacienteDTO) {
        Paciente pacienteModificar = mapper.convertValue(pacienteDTO, Paciente.class);
        pacienteRepository.saveAndFlush(pacienteModificar);
    }
    /* ----------------------------------------------------------------------------- */
}
