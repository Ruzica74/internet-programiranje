package com.example.muzej.controller;

import com.example.muzej.model.KorisnikEntity;
import com.example.muzej.model.RegistrovaniKorisnikEntity;
import com.example.muzej.model.StanjeNalogaEntity;
import com.example.muzej.repository.RegistrovaniKorisnikRepository;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.RegKorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/registrovani")
public class ControllerRegKorisnik {

    @Autowired
    RegKorisnikService service;

    @Autowired
    KorisnikService ser;

    @GetMapping("/{key}")
    public ResponseEntity<List<RegistrovaniKorisnikEntity>> getAllRegistrovani(
            @PathVariable("key") String key){
        if(ser.provjera(key)){
            return ResponseEntity.ok(service.getRegKorisnike());
        }else return ResponseEntity.badRequest().build();
    }

    @PutMapping("/azuriraj/{kIme}/{key}")
    public ResponseEntity<Integer> updateReg(@PathVariable("key") String key,
        @PathVariable("kIme") String kIme,@RequestBody String rijec){
        System.out.println("body: "+rijec);
        if(ser.provjera(key)){
            System.out.println("body: "+rijec);
            try {
                RegistrovaniKorisnikEntity r = service.getReg(kIme);
                StanjeNalogaEntity s = new StanjeNalogaEntity();
                if ("odobri".equals(rijec)) {
                    s.setStanje("odobren");
                    s.setId(1);
                    r.setStanjeNaloga(s);
                } else if ("blokiraj".equals(rijec)) {
                    s.setStanje("blokiran");
                    s.setId(2);
                    r.setStanjeNaloga(s);
                } else {
                    r.getKorisnik().setLozinka(rijec);
                }
                service.updateReg(r);
                return ResponseEntity.ok(1);
            }catch (Exception e){
                return ResponseEntity.badRequest().build();
            }
        }else return ResponseEntity.badRequest().build();
    }

}
