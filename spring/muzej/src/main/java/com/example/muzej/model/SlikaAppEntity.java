package com.example.muzej.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "slika_app")
public class SlikaAppEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "slika")
    private String slika;

}
