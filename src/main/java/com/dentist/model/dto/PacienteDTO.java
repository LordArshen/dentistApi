package com.dentist.model.dto;

import com.dentist.model.entity.Domicilio;

import java.time.LocalDate;

public class PacienteDTO {

    // ATRIBUTOS

    private Integer id;
    private String lastname;
    private String name;
    private String dni;
    private LocalDate dateAdmin;
    private Domicilio domicilio;

    // GETTERS

    public String getLastname() {
        return lastname;
    }

    public String getName() {
        return name;
    }

    public String getDni() {
        return dni;
    }

    public LocalDate getDateAdmin() {
        return dateAdmin;
    }

    public Integer getId() {
        return id;
    }

    // SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setDateAdmin(LocalDate dateAdmin) {
        this.dateAdmin = dateAdmin;
    }

    public void setDomicilio(Domicilio domicilio) {
        this.domicilio = domicilio;
    }

    public void setLastname(String Lastname) {
        this.lastname = Lastname;
    }

    public void setName(String name) {
        this.name = name;
    }
}
