package com.example.muzej.service;

import com.example.muzej.model.TransakcijaEntity;
import com.example.muzej.repository.TransakcijaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransakcijaService {

    @Autowired
    TransakcijaRepository repository;

    public TransakcijaEntity postTransakcija(TransakcijaEntity t){
        return repository.saveAndFlush(t);
    }
}
