package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "prezentacija")
public class PrezentacijaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "video")
    private String video;
    @OneToMany(mappedBy = "prezentacijaByPrezentacijaId")
    @JsonIgnore
    private Collection<PosjetaEntity> posjetasById;
    @ManyToOne
    @JoinColumn(name = "muzej_id", referencedColumnName = "id", nullable = false)
    private MuzejEntity muzejByMuzejId;
    @OneToMany(mappedBy = "prezentacija")
    @JsonIgnore
    private Collection<SlikaEntity> slikasById;

}
