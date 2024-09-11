package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "logovanje_aktivnosti")
public class LogovanjeAktivnostiEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "aktivnost")
    private String aktivnost;
    @ManyToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id")
    private KorisnikEntity korisnikByKorisnikId;
    @Basic
    @Column(name = "vrijeme")
    private Timestamp vrijeme;
}
