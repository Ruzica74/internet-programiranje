package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "lokacija")
public class LokacijaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "drzava")
    private String drzava;
    @Basic
    @Column(name = "grad")
    private String grad;
    @Basic
    @Column(name = "geolokacija_duzina")
    private Double geolokacijaDuzina;
    @Basic
    @Column(name = "geolokacija_sirina")
    private Double geolokacijaSirina;
    @Basic
    @Column(name = "adresa")
    private String adresa;
    @OneToMany(mappedBy = "lokacija")
    @JsonIgnore
    private Collection<MuzejEntity> muzejsById;
}
