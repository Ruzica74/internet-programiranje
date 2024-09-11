package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tip_kartice")
public class TipKarticeEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "tip")
    private String tip;

    @OneToMany(mappedBy = "tipKarticeByTipKarticeId")
    @JsonIgnore
    private List<KarticaEntity> karticas;

}
