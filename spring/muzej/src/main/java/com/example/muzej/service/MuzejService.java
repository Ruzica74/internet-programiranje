package com.example.muzej.service;

import com.example.muzej.model.LokacijaEntity;
import com.example.muzej.model.MuzejEntity;
import com.example.muzej.repository.LokacijaRepository;
import com.example.muzej.repository.MuzejRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MuzejService {

    @Autowired
    MuzejRepository repository;

    @Autowired
    LokacijaRepository repositoryLok;

    public List<MuzejEntity> getAll(){
        return repository.findAll();
    }

    public void input(MuzejEntity m){
        LokacijaEntity lok=repositoryLok.saveAndFlush(m.getLokacija());
        m.setLokacija(lok);
        repository.saveAndFlush(m);
    }
}
