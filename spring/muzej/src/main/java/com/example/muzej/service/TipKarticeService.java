package com.example.muzej.service;
import com.example.muzej.model.TipKarticeEntity;
import com.example.muzej.repository.TipKarticeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TipKarticeService {

    @Autowired
    TipKarticeRepository repository;

    public List<TipKarticeEntity> getTipove(){
        return repository.findAll();
    }
}
