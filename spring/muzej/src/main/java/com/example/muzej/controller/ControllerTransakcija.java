package com.example.muzej.controller;


import com.example.muzej.model.TransakcijaEntity;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.TransakcijaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.util.Date;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/transakcija")
public class ControllerTransakcija {

    @Autowired
    TransakcijaService service;

    @Autowired
    KorisnikService ser;

    @PostMapping("/{key}")
    public ResponseEntity<Boolean> postTrans(@PathVariable("key") String key, @RequestBody TransakcijaEntity t){
        if(ser.provjera(key)){
            try {
                Timestamp time = new Timestamp(new Date().getTime());
                t.setDatum(time);
                service.postTransakcija(t);
                return ResponseEntity.ok(true);
            }catch (Exception e){
                return ResponseEntity.ok(false);
            }
        }return ResponseEntity.badRequest().build();
    }
}
