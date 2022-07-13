package com.dentist.model.dto;

public class OdontologoDTO {

    // ATRIBUTOS

    private Integer id;
    private Integer registrationNumber;
    private String lastName;
    private String name;

    // GETTERS
    public Integer getRegistrationNumber() {
        return registrationNumber;
    }

    public String getLastName() {
        return lastName;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    // SETTERS

    public void setId(Integer id) {
        this.id = id;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setRegistrationNumber(Integer registrationNumber) {
        this.registrationNumber = registrationNumber;
    }
}
