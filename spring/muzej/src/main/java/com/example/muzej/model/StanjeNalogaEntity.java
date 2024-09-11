package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "stanje_naloga")
public class StanjeNalogaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "stanje")
    private String stanje;

    @OneToMany(mappedBy = "stanjeNaloga")
    @JsonIgnore
    private List<RegistrovaniKorisnikEntity> registrovaniKorisniks;

}
