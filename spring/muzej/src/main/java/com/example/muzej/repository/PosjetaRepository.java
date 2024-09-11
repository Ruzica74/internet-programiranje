package com.example.muzej.repository;

import com.example.muzej.model.MuzejEntity;
import com.example.muzej.model.PosjetaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface PosjetaRepository extends JpaRepository<PosjetaEntity, Integer> {
    public List<PosjetaEntity> findAllByPrezentacijaByPrezentacijaId_MuzejByMuzejId_Id(int id);
}
