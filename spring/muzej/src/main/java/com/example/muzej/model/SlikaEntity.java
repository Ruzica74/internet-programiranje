package com.example.muzej.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "slika")
public class SlikaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "slika")
    private String slika;
    @ManyToOne
    @JoinColumn(name = "prezentacija_id", referencedColumnName = "id", nullable = false)
    private PrezentacijaEntity prezentacija;

}
