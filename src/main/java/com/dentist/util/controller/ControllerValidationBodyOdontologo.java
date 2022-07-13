package com.dentist.util.controller;

import com.dentist.model.dto.OdontologoDTO;
import com.dentist.util.IValidation;
import org.springframework.stereotype.Component;

@Component
public class ControllerValidationBodyOdontologo implements IValidation<OdontologoDTO> {

    @Override
    public boolean validate(OdontologoDTO odontologoDTO) {
        return odontologoDTO.getName() != null && odontologoDTO.getLastName() != null && odontologoDTO.getRegistrationNumber() != null;
    }
}
