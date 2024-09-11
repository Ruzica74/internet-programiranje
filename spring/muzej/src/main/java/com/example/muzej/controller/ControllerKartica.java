package com.example.muzej.controller;

import com.example.muzej.model.KarticaEntity;
import com.example.muzej.model.TipKarticeEntity;
import com.example.muzej.service.KarticaService;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.TipKarticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/kartica")
public class ControllerKartica {

    @Autowired
    KarticaService karticaService;

    @Autowired
    KorisnikService service;

    @Autowired
    TipKarticeService tipKarticeService;

    @GetMapping("/{id}/{key}")
    public ResponseEntity<Boolean> hasKartica(@PathVariable("id") Integer id, @PathVariable("key") String key){
        if(service.provjera(key)){
            return ResponseEntity.ok(karticaService.hasKartica(id));
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/tipovi/{key}")
    public ResponseEntity<List<TipKarticeEntity>> getTipove(@PathVariable("key") String key){
        if(service.provjera(key)){
            return ResponseEntity.ok(tipKarticeService.getTipove());
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/brojKartice/{broj}/{key}")
    public ResponseEntity<KarticaEntity> getKartica(@PathVariable("broj") String broj, @PathVariable("key") String key){
        if(service.provjera(key)){
            try {
                KarticaEntity k = karticaService.getKartica(broj);
                return ResponseEntity.ok(k);
            }catch (Exception e){
                return ResponseEntity.ok(null);
            }
        }else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/update/{key}")
    public ResponseEntity<Boolean> update(@PathVariable("key") String key, @RequestBody KarticaEntity k){
        if(service.provjera(key)){
            try {
                karticaService.putKartica(k);
                return ResponseEntity.ok(true);
            }catch (Exception e){
                return ResponseEntity.ok(false);
            }
        }else return ResponseEntity.badRequest().build();
    }

}
