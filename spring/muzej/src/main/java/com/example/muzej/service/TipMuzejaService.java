package com.example.muzej.service;

import com.example.muzej.model.TipMuzejaEntity;
import com.example.muzej.repository.TipMuzejaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipMuzejaService {

    @Autowired
    TipMuzejaRepository repository;

    public List<TipMuzejaEntity> getAll(){
        return repository.findAll();
    }

    public TipMuzejaEntity insertTip(TipMuzejaEntity t){
        return repository.saveAndFlush(t);
    }
}
