package com.example.muzej.repository;

import com.example.muzej.model.TipMuzejaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipMuzejaRepository extends JpaRepository<TipMuzejaEntity, Integer> {
}
