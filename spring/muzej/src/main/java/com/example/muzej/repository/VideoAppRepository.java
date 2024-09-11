package com.example.muzej.repository;

import com.example.muzej.model.VideoAppEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoAppRepository extends JpaRepository<VideoAppEntity, Integer> {
}
