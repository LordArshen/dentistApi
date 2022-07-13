package com.dentist.util.controller;

import com.dentist.model.dto.PacienteDTO;
import com.dentist.util.IValidation;
import org.springframework.stereotype.Component;

@Component
public class ControllerValidationBodyPaciente implements IValidation<PacienteDTO> {

    @Override
    public boolean validate(PacienteDTO pacienteDTO) {
        return pacienteDTO.getName() != null && pacienteDTO.getLastname() != null && pacienteDTO.getDni() != null;
    }
}
