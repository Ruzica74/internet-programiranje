package com.example.muzej.repository;

import com.example.muzej.model.TipKarticeEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipKarticeRepository extends JpaRepository<TipKarticeEntity, Integer> {
}
