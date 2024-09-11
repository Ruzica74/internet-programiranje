package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "muzej")
public class MuzejEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "naziv")
    private String naziv;
    @Basic
    @Column(name = "broj_telefona")
    private String brojTelefona;
    @ManyToOne
    @JoinColumn(name = "lokacija_id", referencedColumnName = "id", nullable = false)
    private LokacijaEntity lokacija;
    @ManyToOne
    @JoinColumn(name = "tip_muzeja_id", referencedColumnName = "id", nullable = false)
    private TipMuzejaEntity tipMuzeja;
    @OneToMany(mappedBy = "muzejByMuzejId")
    @JsonIgnore
    private Collection<PrezentacijaEntity> prezentacijasById;

}
