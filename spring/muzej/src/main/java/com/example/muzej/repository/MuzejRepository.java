package com.example.muzej.repository;

import com.example.muzej.model.MuzejEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MuzejRepository extends JpaRepository<MuzejEntity, Integer> {
}
