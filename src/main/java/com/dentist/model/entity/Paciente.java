package com.dentist.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "patient")
public class Paciente {

    // ATRIBUTOS
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(name = "lastname")
    private String lastname;
    @Column(name = "name")
    private String name;
    @Column(name = "dni")
    private String dni;
    @Column(name = "date")
    private LocalDate dateAdmin;

    @OneToOne(cascade = {CascadeType.REMOVE, CascadeType.REFRESH, CascadeType.PERSIST})
    @JoinColumn(name = "address_id", referencedColumnName = "id")
    private Domicilio domicilio;

    @OneToMany(mappedBy = "paciente")
    @JsonIgnore
    private Set<Turno> listadoTurnos = new HashSet<>();

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

    public Domicilio getDomicilio() {
        return domicilio;
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
}
