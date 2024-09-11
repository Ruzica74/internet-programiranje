package com.example.muzej.service;

import com.example.muzej.model.KorisnikEntity;
import com.example.muzej.model.RegistrovaniKorisnikEntity;
import com.example.muzej.model.StanjeNalogaEntity;
import com.example.muzej.repository.KorisnikRepository;
import com.example.muzej.repository.RegistrovaniKorisnikRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class KorisnikService {
    @Autowired
    KorisnikRepository repository;

    @Autowired
    RegistrovaniKorisnikRepository reg;

    public int getAllAktivni(){
        return repository.countAllByRegistrovaniKorisnikIsNotNullAndAktivanIsTrue();
    }

    public KorisnikEntity getKorisnikByToken(String token){
        return repository.findByToken(token);
    }

    public KorisnikEntity getKorisnikByKIme(String kIme){
        return repository.findByKorisnickoIme(kIme);
    }

    public List<KorisnikEntity> getAll(){
        return repository.findAll();
    }

    public KorisnikEntity getKorisnikById(int id){
        return repository.findById(id).get();
    }

    public void updateAktivnost(KorisnikEntity k){

        repository.save(k);
    }

    public List<String> getAllAdmin(){
        return repository.getAllAdminToken();
    }

    public boolean provjera(String str){
        List<KorisnikEntity> lista=repository.findAll();
        if(str.equals("token123"))
            return true;
        for(KorisnikEntity k: lista){
            if(k.getToken().equals(str))
                return true;
        }
        return false;
    }

    public boolean provjeraAdmin(String str){
        List<String> admin=repository.getAllAdminToken();
        for(String s : admin){
            if(s.equals(str))
                return true;
        }
        return false;
    }

    public void insertKorisnik(KorisnikEntity k){
        repository.saveAndFlush(k);

    }

    public void update(KorisnikEntity k){
        k.setToken("korisnik"+repository.findByKorisnickoIme(k.getKorisnickoIme()).getId());

        repository.save(k);

        StanjeNalogaEntity s=new StanjeNalogaEntity();
        s.setId(3);
        s.setStanje("cekanje");
        RegistrovaniKorisnikEntity registrovani=new RegistrovaniKorisnikEntity();
        registrovani.setKorisnikId(k.getId());
        registrovani.setKorisnik(repository.findByKorisnickoIme(k.getKorisnickoIme()));
        registrovani.setStanjeNaloga(s);
        System.out.println(registrovani.getKorisnikId()+" stanje:"+registrovani.getStanjeNaloga().getStanje());
        reg.saveAndFlush(registrovani);

    }

    public KorisnikEntity provjeraKorImena(String kIme){
        KorisnikEntity k;
        try{
            k=repository.findByKorisnickoIme(kIme);
        }catch(Exception e){
            k=null;
        }
        return k;
    }


}
