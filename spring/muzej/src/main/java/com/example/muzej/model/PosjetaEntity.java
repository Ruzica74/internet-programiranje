package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Time;
import java.util.Collection;
import java.util.Date;

@Data
@Entity
@Table(name = "posjeta")
public class PosjetaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "datum")
    private Date datum;
    @Basic
    @Column(name = "vrijeme")
    private Time vrijeme;
    @Basic
    @Column(name = "trajanje")
    private Double trajanje;
    @Basic
    @Column(name = "cijena")
    private Double cijena;

    @OneToMany(mappedBy = "posjetaByPosjetaId")
    @JsonIgnore
    private Collection<KartaEntity> kartasById;
    @ManyToOne
    @JoinColumn(name = "prezentacija_id", referencedColumnName = "id", nullable = false)
    private PrezentacijaEntity prezentacijaByPrezentacijaId;

}
