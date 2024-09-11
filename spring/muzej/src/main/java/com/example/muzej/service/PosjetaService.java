package com.example.muzej.service;

import com.example.muzej.model.PosjetaEntity;
import com.example.muzej.repository.PosjetaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.util.List;

@Service
public class PosjetaService {

    @Autowired
    PosjetaRepository repository;

    public List<PosjetaEntity> getAll(){
        return repository.findAll();
    }

    public PosjetaEntity inputPosjeta(PosjetaEntity p){
        return repository.saveAndFlush(p);
    }

    public List<PosjetaEntity> getPosjete(int id){
        java.util.Date utilDatum=new java.util.Date();
        Date datum=new Date(utilDatum.getTime());
        return  repository.findAllByPrezentacijaByPrezentacijaId_MuzejByMuzejId_Id(id);
    }

    public PosjetaEntity getPosjetu(int idPosjete){
        return repository.findById(idPosjete).get();
    }


}
