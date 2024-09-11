package com.example.muzej.repository;

import com.example.muzej.model.PrezentacijaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PrezentacijaRepository extends JpaRepository<PrezentacijaEntity, Integer> {
}
