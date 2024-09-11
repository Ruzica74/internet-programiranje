package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "tip_muzeja")
public class TipMuzejaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "tip")
    private String tip;
    @OneToMany(mappedBy = "tipMuzeja")
    @JsonIgnore
    private List<MuzejEntity> muzejs;
}
