package com.example.muzej.service;

import com.example.muzej.model.SlikaEntity;
import com.example.muzej.repository.SlikaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SlikaService {

    @Autowired
    SlikaRepository repository;

    public SlikaEntity putSlika(SlikaEntity s){
        return repository.saveAndFlush(s);
    }

    public List<SlikaEntity> getSlike(int id){
        return repository.findSlikaEntitiesByPrezentacija_Id(id);
    }
}
