package com.example.muzej.controller;


import com.example.muzej.model.KorisnikEntity;
import com.example.muzej.model.LogovanjeAktivnostiEntity;
import com.example.muzej.model.RegistrovaniKorisnikEntity;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.LogovanjeAktivnostiService;
import com.example.muzej.service.RegKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/logovanje")
public class ControllerLogovanjeAktivnosti {

    @Autowired
    LogovanjeAktivnostiService service;

    @Autowired
    KorisnikService ser;

    @Autowired
    RegKorisnikService reg;

    @GetMapping("/{key}")
    public ResponseEntity<List<LogovanjeAktivnostiEntity>> getAll(@PathVariable String key){
        System.out.println("kljuc:" +key);
        if(ser.provjeraAdmin(key)){
            List<LogovanjeAktivnostiEntity> lista=service.getAllLogovanja();
            System.out.println("listaa: ");
            return ResponseEntity.ok(lista);
        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{key}")
    public ResponseEntity<Integer> inputLog(@PathVariable("key") String key,@RequestBody String akcija ){
        if(ser.provjera(key)){
            KorisnikEntity k=ser.getKorisnikByToken(key);
            LogovanjeAktivnostiEntity l=new LogovanjeAktivnostiEntity();
            l.setAktivnost(akcija);
            Date date=new Date();
            Timestamp t=new Timestamp(date.getTime());
            l.setVrijeme(t);
            l.setKorisnikByKorisnikId(k);
            System.out.println("korisnik: "+l.getKorisnikByKorisnikId().getKorisnickoIme());
            try{
                service.inputLog(l);
                return ResponseEntity.ok(1);
            }catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.ok(0);
            }
        }else return ResponseEntity.badRequest().build();
    }
}
