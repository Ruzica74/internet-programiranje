package com.example.muzej.controller;

import com.example.muzej.model.AdminEntity;
import com.example.muzej.service.AktivnostService;
import com.example.muzej.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*" )
@RestController
@RequestMapping("/aktivnost")
public class ControllerAktivnost {

    @Autowired
    AktivnostService service;

    @Autowired
    KorisnikService serv;

    @PostMapping("/{key}")
    public ResponseEntity<Integer> insert(@RequestBody() String v, @PathVariable("key") String key){
        if(serv.provjera(key)){
            Timestamp vrijeme=Timestamp.valueOf(v);
           service.insert(vrijeme);
            return ResponseEntity.ok(1);
        }else return ResponseEntity.badRequest().build();
    }


    @GetMapping("/{key}")
    public ResponseEntity<List<Integer>> getAktivnostPoSatima(
     @PathVariable("key") String key){
        if(serv.provjeraAdmin(key)){
            Timestamp sati=new Timestamp(new Date().getTime());
            sati.setMinutes(0);
            sati.setSeconds(0);
            List<Integer> lista=new ArrayList<>();
            boolean b=true;
            int k=0;
            for(int i=0; i<24 ;i++) {

                Timestamp time=new Timestamp(sati.getTime());
                Timestamp time1=new Timestamp(sati.getTime());
                time.setHours(sati.getHours()-i-1);
                time1.setHours(sati.getHours()-i);
                System.out.println("time: "+time+" , time1:"+time1);
                lista.add(service.getAktivnost(time, time1));
            }
            return ResponseEntity.ok(lista);
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/registrovani/{key}")
    public ResponseEntity<Integer> getBrojRegistrovanih( @PathVariable("key") String key){
        if(serv.provjeraAdmin(key)){
            int a= service.getBrojRegistrovanih();
            return ResponseEntity.ok(a);
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/aktivni/{key}")
    public ResponseEntity<Integer> getBrojAktivnih( @PathVariable("key") String key){
        if(serv.provjeraAdmin(key)){
            int a= serv.getAllAktivni();
            return ResponseEntity.ok(a);
        }else return ResponseEntity.badRequest().build();
    }


}
