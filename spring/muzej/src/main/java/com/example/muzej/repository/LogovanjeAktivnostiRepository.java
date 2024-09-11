package com.example.muzej.repository;

import com.example.muzej.model.LogovanjeAktivnostiEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface LogovanjeAktivnostiRepository extends JpaRepository<LogovanjeAktivnostiEntity, Integer> {


}
