package com.example.muzej.repository;

import com.example.muzej.model.LokacijaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LokacijaRepository extends JpaRepository<LokacijaEntity, Integer> {

    public LokacijaEntity findByAdresa(String adresa);
}
