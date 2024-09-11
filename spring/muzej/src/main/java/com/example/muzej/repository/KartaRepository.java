package com.example.muzej.repository;

import com.example.muzej.model.KartaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface KartaRepository extends JpaRepository<KartaEntity, Integer> {


    List<KartaEntity> findKartaEntitiesByRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId_KorisnikId(int id);


}
