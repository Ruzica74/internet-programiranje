package com.example.muzej.service;

import com.example.muzej.model.KartaEntity;
import com.example.muzej.repository.KartaRepository;
import com.example.muzej.repository.KarticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KartaService {

    @Autowired
    KartaRepository repository;

    public KartaEntity postKarta(KartaEntity k){
        return repository.saveAndFlush(k);
    }

    public List<KartaEntity> getAll(){
        return  repository.findAll();
    }

    public List<KartaEntity> getAktivne(int id){
        return  repository.findKartaEntitiesByRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId_KorisnikId(id);
    }

    public List<KartaEntity> getDanasnjeKArte(){
        return repository.findAll();
    }
}
