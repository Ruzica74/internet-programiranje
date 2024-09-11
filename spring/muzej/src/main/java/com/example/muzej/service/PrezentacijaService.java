package com.example.muzej.service;

import com.example.muzej.model.PrezentacijaEntity;
import com.example.muzej.repository.PrezentacijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PrezentacijaService {

    @Autowired
    PrezentacijaRepository repository;

    public PrezentacijaEntity findPrezentacija(int id){
        return repository.findById(id).get();
    }

    public PrezentacijaEntity inputPrezentacija(PrezentacijaEntity p){
        return repository.saveAndFlush(p);
    }
}
