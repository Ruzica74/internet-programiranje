package com.example.muzej.service;

import com.example.muzej.model.AktivnostEntity;
import com.example.muzej.repository.AktivnostRepository;
import com.example.muzej.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;

@Service
public class AktivnostService {

    @Autowired
    AktivnostRepository repository;

    @Autowired
    RegistrovaniKorisnikRepository registrovaniKorisnikRepository;

    public int getAktivnost(Timestamp odSati, Timestamp doSati){
        return repository.getBrojAktivnih(odSati, doSati);
    }

    public int getBrojRegistrovanih(){
        return registrovaniKorisnikRepository.prebroj();
    }

    public void insert(Timestamp vrijeme){
        AktivnostEntity a=new AktivnostEntity();
        a.setVrijeme(vrijeme);
        repository.saveAndFlush(a);
    }
}
