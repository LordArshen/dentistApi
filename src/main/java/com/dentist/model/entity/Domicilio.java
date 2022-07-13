package com.dentist.model.entity;

import javax.persistence.*;

@Entity
@Table(name = "address")
public class Domicilio {

    //ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "street")
    private String address;
    @Column(name = "number")
    private Integer number;
    @Column(name = "city")
    private String locationCity;
    @Column(name = "state")
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
