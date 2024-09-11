package com.example.muzej.controller;

import com.example.muzej.model.MuzejEntity;
import com.example.muzej.model.TipKarticeEntity;
import com.example.muzej.model.TipMuzejaEntity;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.MuzejService;
import com.example.muzej.service.TipMuzejaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/muzeji")
public class ControllerMuzej {

   @Autowired
    KorisnikService service;

    @Autowired
    MuzejService muzej;

    @Autowired
    TipMuzejaService serviceTip;

    @GetMapping("/{key}")
    public ResponseEntity<List<MuzejEntity>> getMuzeji(@PathVariable("key") String key){
        if(service.provjera(key)){
            List<MuzejEntity> lista=muzej.getAll();
        return ResponseEntity.ok(lista);
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/tip/{key}")
    public ResponseEntity<List<TipMuzejaEntity>> getTipove(@PathVariable("key") String key){
        if(service.provjera(key)){
            List<TipMuzejaEntity> lista=serviceTip.getAll();
            return ResponseEntity.ok(lista);
        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/tip/{key}")
    public ResponseEntity<TipMuzejaEntity> insertTip(@PathVariable("key") String key, @RequestBody TipMuzejaEntity t){
        if(service.provjera(key)){
            try{
                TipMuzejaEntity tip=serviceTip.insertTip(t);

                return ResponseEntity.ok(tip);
            }catch(Exception e){
             return ResponseEntity.badRequest().build();
            }
        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{key}")
    public ResponseEntity<Integer> insertMuzej(@PathVariable("key") String key, @RequestBody MuzejEntity m){
        if(service.provjeraAdmin(key)){
            try{
                muzej.input(m);
                return ResponseEntity.ok(1);
            }catch(Exception e){
                e.printStackTrace();
                return ResponseEntity.ok(0);
            }
        }else return ResponseEntity.badRequest().build();
    }



}
