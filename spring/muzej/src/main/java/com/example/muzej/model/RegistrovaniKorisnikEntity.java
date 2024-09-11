package com.example.muzej.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@Table(name = "registrovani_korisnik")
public class RegistrovaniKorisnikEntity {
    @Id
    @Column(name = "korisnik_id")
    private Integer korisnikId;

    @OneToMany(mappedBy = "registrovaniKorisnikByRegistrovaniKorisnikKorisnikId")
    @JsonIgnore
    private List<KartaEntity> kartas;

    @OneToMany(mappedBy = "registrovaniKorisnikByRegistrovaniKorisnikKorisnikId")
    @JsonIgnore
    private List<KarticaEntity> karticas;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnik;
    @ManyToOne
    @JoinColumn(name = "stanje_naloga_id", referencedColumnName = "id", nullable = false)
    private StanjeNalogaEntity stanjeNaloga;

}
