package com.example.muzej.repository;

import com.example.muzej.model.TransakcijaEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransakcijaRepository extends JpaRepository<TransakcijaEntity, Integer> {
}
