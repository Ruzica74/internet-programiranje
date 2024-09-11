package com.example.muzej.controller;

import com.example.muzej.model.KorisnikEntity;
import com.example.muzej.model.RegistrovaniKorisnikEntity;
import com.example.muzej.model.StanjeNalogaEntity;
import com.example.muzej.service.AktivnostService;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.RegKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/korisnik")
public class ControllerKorisnik {
    @Autowired
    KorisnikService service;

    @Autowired
    AktivnostService aktivnost;

    @Autowired
    RegKorisnikService reg;



    @GetMapping("/kIme/{kIme}/{key}")
    public ResponseEntity<KorisnikEntity> getByKIme(@PathVariable("kIme") String kIme, @PathVariable("key") String key){
        if(service.provjera(key)){
            KorisnikEntity k= service.getKorisnikByKIme(kIme);
            RegistrovaniKorisnikEntity r=reg.getReg(kIme);
            if((r!=null && r.getStanjeNaloga().getStanje().equals("odobren")) || r==null)
            {
                return ResponseEntity.ok(k);}
            else return ResponseEntity.badRequest().build();
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/id/{id}/{key}")
    public ResponseEntity<KorisnikEntity> getById(@PathVariable("id") int id, @PathVariable("key") String key){
        if(service.provjera(key)){
            KorisnikEntity k= service.getKorisnikById(id);
            return ResponseEntity.ok(k);
        }else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/aktivnost/{key}")
    public ResponseEntity<Boolean> updateAktivan(@RequestBody KorisnikEntity k,@PathVariable("key") String key){
        System.out.println(k.getAktivan());
        if(service.provjera(key)){
            service.updateAktivnost(k);
            if(k.getAktivan())
                {Timestamp time=new Timestamp(new Date().getTime());
                 aktivnost.insert(time);}
            return ResponseEntity.ok(true);
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping()
    public List<KorisnikEntity> getAll(){
        return service.getAll();
    }

    @GetMapping("/provjera/{kIme}/{key}")
    public ResponseEntity<KorisnikEntity> getProvjeraImena(@PathVariable("kIme") String kIme, @PathVariable("key") String key){
        if(service.provjera(key)){
            KorisnikEntity k= service.provjeraKorImena(kIme);
            return ResponseEntity.ok(k);
        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{key}")
    public ResponseEntity<Integer> input(@RequestBody KorisnikEntity body, @PathVariable("key") String key){
        if(service.provjera(key)){
            System.out.print(body.getKorisnickoIme()+", ime:"+ body.getIme());
            try {
                service.insertKorisnik(body);
                KorisnikEntity korisnik= service.getKorisnikByKIme(body.getKorisnickoIme());
                service.update(korisnik);
                return ResponseEntity.ok(1);
            }catch(Exception e)
            {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }else return ResponseEntity.badRequest().build();
    }





}
