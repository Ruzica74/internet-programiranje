package com.example.muzej.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "karta")
public class KartaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;
    @Basic
    @Column(name = "broj_karte")
    private Integer brojKarte;
    @Basic
    @Column(name = "placena")
    private Boolean placena;
    @ManyToOne
    @JoinColumn(name = "posjeta_id", referencedColumnName = "id", nullable = false)
    private PosjetaEntity posjetaByPosjetaId;
    @ManyToOne
    @JoinColumn(name = "registrovani_korisnik_korisnik_id", referencedColumnName = "korisnik_id", nullable = false)
    private RegistrovaniKorisnikEntity registrovaniKorisnikByRegistrovaniKorisnikKorisnikId;

}
