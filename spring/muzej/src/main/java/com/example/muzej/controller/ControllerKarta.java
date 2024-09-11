package com.example.muzej.controller;


import com.example.muzej.model.KartaEntity;
import com.example.muzej.model.PosjetaEntity;
import com.example.muzej.other.EmailServiceImpl;
import com.example.muzej.other.UserPDFExporter;
import com.example.muzej.service.KartaService;
import com.example.muzej.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/karta")
public class ControllerKarta {

    @Autowired
    KartaService service;

    @Autowired
    KorisnikService ser;

    @Autowired
    EmailServiceImpl email;

    @PostMapping("/{key}")
    public ResponseEntity<Boolean> postKarta(@PathVariable("key") String key, @RequestBody KartaEntity k) {
        if (ser.provjera(key)) {
            try {
                List<KartaEntity> lista=service.getAll();
                boolean nadjen;
                Random rand=new Random();
                UserPDFExporter pdf=new UserPDFExporter();
                int brojKarte;
                do{
                    nadjen=true;
                    brojKarte=rand.nextInt(100000);
                    for(KartaEntity kar : lista){
                        if(kar.getBrojKarte()==brojKarte){
                            nadjen=false;
                        }
                    }
                }while(!nadjen);

                k.setBrojKarte(brojKarte);
                service.postKarta(k);
                pdf.export(k);
                email.sendMail(k.getRegistrovaniKorisnikByRegistrovaniKorisnikKorisnikId().getKorisnik().getMail());
                return ResponseEntity.ok(true);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.ok(false);
            }
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/{idKorisnika}/{key}")
    public ResponseEntity<List<PosjetaEntity>> getAktineKarte(@PathVariable("idKorisnika") int id, @PathVariable("key") String key){
        if(ser.provjera(key)){
            try {
                List<KartaEntity> lista=service.getAktivne(id);
                List<PosjetaEntity> posjete=new ArrayList<>();
                ArrayList<PosjetaEntity> izbaci=new ArrayList<>();
                for(KartaEntity k :lista){
                    posjete.add(k.getPosjetaByPosjetaId());
                }
                if(posjete!=null){

                    for(PosjetaEntity p : posjete) {

                        Time vrijeme = p.getVrijeme();
                        LocalTime time = vrijeme.toLocalTime();
                        Double d = p.getTrajanje() * 60;
                        int i = d.intValue();
                        time = time.plusMinutes(i);
                        LocalTime sada = LocalTime.now();
                        java.util.Date utilDatum = new java.util.Date();
                        java.sql.Date datum = new java.sql.Date(utilDatum.getTime());

                        java.sql.Date datum1=new java.sql.Date(p.getDatum().getTime());
                        System.out.println("Vrijeme je l poslije: "+time.isBefore(sada));
                        System.out.println("Je l danasnji datum: "+datum.toString().equals(datum1.toString()));
                        System.out.println((p.getDatum().compareTo(datum)<0));
                        if ( (datum.toString().equals(datum1.toString()) && time.isBefore(sada)) || (!datum.toString().equals(datum1.toString()) && p.getDatum().compareTo(datum)<0)) {
                            izbaci.add(p);
                            System.out.println("izbacena posjeta id: "+p.getId());
                        }
                    }
                    for(PosjetaEntity pos: izbaci){
                        posjete.remove(pos);
                    }
                }
                return ResponseEntity.ok(posjete);
            }catch (Exception e){
                return ResponseEntity.ok(null);
            }
        }else return ResponseEntity.badRequest().build();
    }


    @GetMapping("/trenutne/{idKorisnik}/{key}")
    public ResponseEntity<List<PosjetaEntity>> getTrenutne(@PathVariable("idKorisnik") int id, @PathVariable("key") String key) {
        if (ser.provjera(key)) {
            try {
                List<KartaEntity> lista=service.getAktivne(id);
                List<PosjetaEntity> posjete=new ArrayList<>();
                ArrayList<PosjetaEntity> odabrane=new ArrayList<>();
                for(KartaEntity k :lista){
                    posjete.add(k.getPosjetaByPosjetaId());
                }
                if(posjete!=null) {

                    for (PosjetaEntity p : posjete) {

                        Time vrijeme = p.getVrijeme();
                        LocalTime pocetak = vrijeme.toLocalTime();
                        Double d = p.getTrajanje() * 60;
                        int i = d.intValue();
                        LocalTime kraj = pocetak.plusMinutes(i);
                        LocalTime sada = LocalTime.now();
                        java.util.Date utilDatum = new java.util.Date();
                        java.sql.Date datum = new java.sql.Date(utilDatum.getTime());

                        java.sql.Date datum1 = new java.sql.Date(p.getDatum().getTime());
                        System.out.println("------Id posjete:"+p.getId());
                        System.out.println("Vrijeme pocetak: " + pocetak.isBefore(sada));
                        System.out.println("Je l danasnji datum: " + datum.toString().equals(datum1.toString()));
                        System.out.println("Vrijeme kraj: " +kraj.isAfter(sada) );
                        if (datum.toString().equals(datum1.toString()) && pocetak.isBefore(sada) && kraj.isAfter(sada)) {
                            odabrane.add(p);
                            System.out.println("Id odabrane: "+p.getId());
                        }
                    }
                }
                return ResponseEntity.ok(odabrane);
            } catch (Exception e) {
                return ResponseEntity.ok(null);
            }
        } else return ResponseEntity.badRequest().build();
    }

}
