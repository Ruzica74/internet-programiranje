package com.example.muzej.repository;

import com.example.muzej.model.RegistrovaniKorisnikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RegistrovaniKorisnikRepository extends JpaRepository<RegistrovaniKorisnikEntity, Integer> {

    @Query("select count(korisnikId) from RegistrovaniKorisnikEntity ")
    int prebroj();

    RegistrovaniKorisnikEntity findRegistrovaniKorisnikEntityByKorisnik_KorisnickoIme(String kIme);

    RegistrovaniKorisnikEntity findRegistrovaniKorisnikEntityByKorisnik_Token(String token);
}
