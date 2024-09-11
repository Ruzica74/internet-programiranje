package com.example.muzej.controller;

import com.example.muzej.model.PrezentacijaEntity;
import com.example.muzej.model.SlikaEntity;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.PrezentacijaService;
import com.example.muzej.service.SlikaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/prezentacija")
public class ControllerPrezentacija {

    @Autowired
    KorisnikService ser;

    @Autowired
    PrezentacijaService service;

    @Autowired
    SlikaService slikaService;

    @GetMapping("/{id}/{key}")
    public ResponseEntity<PrezentacijaEntity> getPrezentacija(@PathVariable("key") String key, @PathVariable("id") int id){
        if(ser.provjera(key)){
            try {

                return ResponseEntity.ok(service.findPrezentacija(id));
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.ok(null);
            }
        }return ResponseEntity.badRequest().build();
    }

    @GetMapping("/slike/{id}/{key}")
    public ResponseEntity<List<String>> getSlike(@PathVariable("id") int id, @PathVariable("key") String key){
        if(ser.provjera(key)){
            try {
                List<SlikaEntity> lista=slikaService.getSlike(id);
                List<String> slike=new ArrayList<>();
                for(SlikaEntity s : lista){
                    slike.add(s.getSlika());
                }
                return ResponseEntity.ok(slike);
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.ok(null);
            }
        }return ResponseEntity.badRequest().build();
    }

}
