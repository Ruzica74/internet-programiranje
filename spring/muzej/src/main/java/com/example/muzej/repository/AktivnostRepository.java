package com.example.muzej.repository;

import com.example.muzej.model.AktivnostEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;

@Repository
public interface AktivnostRepository extends JpaRepository<AktivnostEntity, Integer> {

    @Query("select count(id) from AktivnostEntity where vrijeme > ?1 and vrijeme < ?2")
    int getBrojAktivnih(Timestamp odSati, Timestamp doSati);
}
