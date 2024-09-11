package com.example.muzej.repository;

import com.example.muzej.model.KorisnikEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KorisnikRepository extends JpaRepository<KorisnikEntity, Integer> {

    KorisnikEntity findByKorisnickoIme(String korisnickoIme);

    @Query(value = "select korisnik.token from korisnik inner join admin on korisnik.id=admin.korisnik_id", nativeQuery = true)
    List<String> getAllAdminToken();

    int countAllByRegistrovaniKorisnikIsNotNullAndAktivanIsTrue();

    KorisnikEntity findByToken(String token);


}
