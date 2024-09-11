package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Data
@Entity
@Table(name = "kartica")
public class KarticaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "broj_kartice")
    private String brojKartice;
    @Basic
    @Column(name = "pin")
    private String pin;
    @Basic
    @Column(name = "stanje_racuna")
    private Double stanjeRacuna;
    @Basic
    @Column(name = "omoguceno_placanje")
    private Boolean omogucenoPlacanje;
    @Basic
    @Column(name = "datum_isticanja")
    private Date datumIsticanja;
    @ManyToOne
    @JoinColumn(name = "registrovani_korisnik_korisnik_id", referencedColumnName = "korisnik_id", nullable = false)
    private RegistrovaniKorisnikEntity registrovaniKorisnikByRegistrovaniKorisnikKorisnikId;
    @ManyToOne
    @JoinColumn(name = "tip_kartice_id", referencedColumnName = "id", nullable = false)
    private TipKarticeEntity tipKarticeByTipKarticeId;
    @OneToMany(mappedBy = "kartica")
    @JsonIgnore
    private Collection<TransakcijaEntity> transakcijasById;

}
