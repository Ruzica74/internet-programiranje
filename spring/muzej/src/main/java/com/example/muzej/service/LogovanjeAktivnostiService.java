package com.example.muzej.service;

import com.example.muzej.model.LogovanjeAktivnostiEntity;
import com.example.muzej.repository.LogovanjeAktivnostiRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogovanjeAktivnostiService {

    @Autowired
    LogovanjeAktivnostiRepository repository;

    public List<LogovanjeAktivnostiEntity> getAllLogovanja(){
        return repository.findAll();
    }

    public int inputLog(LogovanjeAktivnostiEntity l){
        try {

            repository.saveAndFlush(l);
            return 1;
        }catch (Exception e){
            e.printStackTrace();
            return 0;
        }
    }
}
