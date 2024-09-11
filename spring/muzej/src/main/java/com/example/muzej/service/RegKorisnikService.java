package com.example.muzej.service;

import com.example.muzej.model.KorisnikEntity;
import com.example.muzej.model.RegistrovaniKorisnikEntity;
import com.example.muzej.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class RegKorisnikService {

    @Autowired
    RegistrovaniKorisnikRepository repository;

    public List<RegistrovaniKorisnikEntity> getRegKorisnike(){
        return repository.findAll();
    }

    public void updateReg(RegistrovaniKorisnikEntity r){
        repository.save(r);
    }

    public RegistrovaniKorisnikEntity getReg(String kIme){
        return repository.findRegistrovaniKorisnikEntityByKorisnik_KorisnickoIme(kIme);
    }

    public RegistrovaniKorisnikEntity getRegByToken(String token){
        return repository.findRegistrovaniKorisnikEntityByKorisnik_Token(token);
    }



    public void input(RegistrovaniKorisnikEntity r){
        repository.saveAndFlush(r);
    }

}
