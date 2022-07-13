package com.dentist.model.dto;

import java.time.LocalDateTime;

public class TurnoDTO {

    // ATRIBUTOS
    private Integer id;
    private LocalDateTime schedule;
    private PacienteDTO paciente;
    private OdontologoDTO odontologo;

    // GETTERS

    public Integer getId() {
        return id;
    }

    public LocalDateTime getSchedule() {
        return schedule;
    }

    public PacienteDTO getPaciente() {
        return paciente;
    }

    public OdontologoDTO getOdontologo() {
        return odontologo;
    }

    // SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setSchedule(LocalDateTime schedule) {
        this.schedule = schedule;
    }

    public void setPaciente(PacienteDTO paciente) {
        this.paciente = paciente;
    }

    public void setOdontologo(OdontologoDTO odontologo) {
        this.odontologo = odontologo;
    }
}
