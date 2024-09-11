package com.example.muzej.model;

import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "transakcija")
public class TransakcijaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "iznos")
    private Double iznos;
    @Basic
    @Column(name = "datum")
    private Timestamp datum;
    @ManyToOne
    @JoinColumn(name = "kartica_id", referencedColumnName = "id", nullable = false)
    private KarticaEntity kartica;

}
