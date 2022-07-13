package com.dentist.util.controller;

import com.dentist.model.dto.TurnoDTO;
import com.dentist.util.IValidation;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
public class ControllerValidationBodyTurno implements IValidation<TurnoDTO> {

    @Override
    public boolean validate(TurnoDTO turnoDTO) {
        return turnoDTO.getSchedule() != null && turnoDTO.getSchedule().isAfter(LocalDateTime.now()) && turnoDTO.getOdontologo().getId() > 0 && turnoDTO.getPaciente().getId() > 0;
    }
}
