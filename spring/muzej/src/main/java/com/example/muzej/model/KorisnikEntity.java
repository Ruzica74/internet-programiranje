package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Data
@Entity
@Table(name = "korisnik")
public class KorisnikEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "ime")
    private String ime;
    @Basic
    @Column(name = "prezime")
    private String prezime;
    @Basic
    @Column(name = "korisnicko_ime")
    private String korisnickoIme;
    @Basic
    @Column(name = "lozinka")
    private String lozinka;
    @Basic
    @Column(name = "mail")
    private String mail;
    @Basic
    @Column(name = "token")
    private String token;
    @Basic
    @Column(name = "aktivan")
    private Boolean aktivan;
    @OneToOne(mappedBy = "korisnikByKorisnikId")
    @JsonIgnore
    private AdminEntity admin;
    @OneToMany(mappedBy = "korisnikByKorisnikId")
    @JsonIgnore
    private Collection<LogovanjeAktivnostiEntity> logovanjeAktivnostis;
    @OneToOne(mappedBy = "korisnik", cascade = CascadeType.ALL)
    @JsonIgnore
    private RegistrovaniKorisnikEntity registrovaniKorisnik;



}
