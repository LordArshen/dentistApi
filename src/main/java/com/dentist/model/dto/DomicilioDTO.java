package com.dentist.model.dto;

public class DomicilioDTO {

    //ATRIBUTOS
    private Integer id;
    private String address;
    private Integer number;
    private String locationCity;
    private String country;

    //GETTERS
    public String getAddress() {
        return address;
    }

    public Integer getNumber() {
        return number;
    }

    public String getLocation() {
        return locationCity;
    }

    public String getCountry() {
        return country;
    }

    public Integer getId() {
        return id;
    }

    // SETTERS
    public void setId(Integer id) {
        this.id = id;
    }
}
