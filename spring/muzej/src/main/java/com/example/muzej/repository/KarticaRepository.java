package com.example.muzej.repository;

import com.example.muzej.model.KarticaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface KarticaRepository extends JpaRepository<KarticaEntity, Integer> {

    List<KarticaEntity> findAllByRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId_KorisnikId(int id);

    KarticaEntity findKarticaEntityByBrojKartice(String broj);
}
