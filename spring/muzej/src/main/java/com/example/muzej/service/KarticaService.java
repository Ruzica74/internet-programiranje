package com.example.muzej.service;

import com.example.muzej.model.KartaEntity;
import com.example.muzej.model.KarticaEntity;
import com.example.muzej.repository.KarticaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KarticaService {

    @Autowired
    KarticaRepository repository;

    public boolean hasKartica(int id){
        List<KarticaEntity> lista=repository.findAllByRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId_KorisnikId(id);
        if(lista.size()>0){
            return true;
        }else return false;
    }

    public KarticaEntity getKartica(String broj){
        return  repository.findKarticaEntityByBrojKartice(broj);
    }

    public KarticaEntity putKartica(KarticaEntity k){
        return repository.save(k);
    }
}
