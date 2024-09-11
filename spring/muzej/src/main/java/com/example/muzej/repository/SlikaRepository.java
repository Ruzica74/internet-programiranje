package com.example.muzej.repository;

import com.example.muzej.model.SlikaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SlikaRepository extends JpaRepository<SlikaEntity, Integer> {

    List<SlikaEntity> findSlikaEntitiesByPrezentacija_Id(int id);
}
