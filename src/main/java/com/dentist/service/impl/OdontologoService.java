package com.dentist.service.impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.dentist.exception.NotFoundIdException;
import com.dentist.model.dto.OdontologoDTO;
import com.dentist.model.entity.Odontologo;
import com.dentist.repository.IOdontologoRepository;
import com.dentist.service.IOdontologoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OdontologoService implements IOdontologoService {

    @Autowired
    IOdontologoRepository odontologoRepository;

    @Autowired
    ObjectMapper mapper;

   /* ──────────────
     MÉTODOS CRUD
   ────────────── */

    // GUARDAR
    public OdontologoDTO saveAndFlush(OdontologoDTO odontologoDTO) {
        Odontologo odontologoAGuardar = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.saveAndFlush(odontologoAGuardar);
        return mapper.convertValue(odontologoAGuardar, OdontologoDTO.class);
    }
    /* ----------------------------------------------------------------------------- */

    // BUSCAR TODOS
    public List<OdontologoDTO> findAll() {
        List<OdontologoDTO> listaOdontologosDTO = new ArrayList<>();
        List<Odontologo> listaOdontologos = odontologoRepository.findAll();
        for (Odontologo odontologo : listaOdontologos) {
            listaOdontologosDTO.add(mapper.convertValue(odontologo, OdontologoDTO.class));
        }
        return listaOdontologosDTO;
    }
    /* ----------------------------------------------------------------------------- */

    // BUSCAR POR ID
    public OdontologoDTO findById(Integer id) {
        return mapper.convertValue(odontologoRepository.findById(id).orElseThrow(() -> new NotFoundIdException("El Odontólogo con ID " + id + " no fue encontrado")), OdontologoDTO.class);
    }
    /* ----------------------------------------------------------------------------- */

    // ELIMINAR POR ID
    public void deleteById(Integer id) {
        odontologoRepository.findById(id).orElseThrow(() -> new NotFoundIdException("El Odontólogo con ID " + id + " no fue encontrado y no pudo eliminarse"));
        odontologoRepository.deleteById(id);
    }
    /* ----------------------------------------------------------------------------- */

    // MODIFICAR POR ID
    public void update(OdontologoDTO odontologoDTO) {
        Odontologo odontologoModificar = mapper.convertValue(odontologoDTO, Odontologo.class);
        odontologoRepository.saveAndFlush(odontologoModificar);
    }
    /* ----------------------------------------------------------------------------- */

}
