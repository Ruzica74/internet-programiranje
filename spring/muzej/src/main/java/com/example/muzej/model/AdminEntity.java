package com.example.muzej.model;

import lombok.*;

import javax.persistence.*;

@Data
@Entity
@Table(name = "admin")
public class AdminEntity {

    @Id

    @Column(name = "korisnik_id")
    private Integer korisnikId;
    @OneToOne
    @JoinColumn(name = "korisnik_id", referencedColumnName = "id", nullable = false)
    private KorisnikEntity korisnikByKorisnikId;

}
