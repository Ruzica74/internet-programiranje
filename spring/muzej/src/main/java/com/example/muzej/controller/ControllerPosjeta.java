package com.example.muzej.controller;

import com.example.muzej.model.PosjetaEntity;
import com.example.muzej.model.PrezentacijaEntity;
import com.example.muzej.model.SlikaEntity;
import com.example.muzej.other.FileUpoladUtil;
import com.example.muzej.repository.PrezentacijaRepository;
import com.example.muzej.service.KorisnikService;
import com.example.muzej.service.PosjetaService;
import com.example.muzej.service.PrezentacijaService;
import com.example.muzej.service.SlikaService;
import org.apache.tomcat.util.json.JSONParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.net.URI;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "*")
@RequestMapping("/posjeta")
@RestController
public class ControllerPosjeta {

    @Autowired
    PrezentacijaService prezentacija;

    @Autowired
    PosjetaService posjeta;

    @Autowired
    KorisnikService service;

    @Autowired
    SlikaService slikaService;

    @GetMapping("/{id}/{key}")
    public ResponseEntity<List<PosjetaEntity>> getPosjeteZaMuzej(@PathVariable("id") int idMuzeja, @PathVariable("key") String key){
        if(service.provjera(key)){
            List<PosjetaEntity> posjete=posjeta.getPosjete(idMuzeja);
            ArrayList<PosjetaEntity> izbaci=new ArrayList<>();
            if(posjete!=null){

            for(PosjetaEntity p : posjete) {

                Time vrijeme = p.getVrijeme();
                LocalTime time = vrijeme.toLocalTime();
                Double d = p.getTrajanje() * 60;
                int i = d.intValue();
                time = time.plusMinutes(i);
                LocalTime sada = LocalTime.now();
                java.util.Date utilDatum = new java.util.Date();
                Date datum = new Date(utilDatum.getTime());
                Date datum1=new Date(p.getDatum().getTime());
                System.out.println("id posjete: " +p.getId() + (datum1.toString().equals(datum.toString())) + datum1+ datum);
                //System.out.println((datum1.compareTo(datum) == 0 ) +" compare posjeteee");
                //if ((datum.compareTo(p.getDatum())<0) || (datum1.toString().equals(datum.toString()) && time.isBefore(sada))) {
                  if((datum.toString().equals(datum1.toString()) && time.isBefore(sada)) || (!datum.toString().equals(datum1.toString()) && p.getDatum().compareTo(datum)<0)){
                    izbaci.add(p);

                }
            }
            for(PosjetaEntity pos: izbaci){
                posjete.remove(pos);
            }
            }
            return ResponseEntity.ok(posjete);
        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{key}")
    public ResponseEntity<Integer> insertPosjeta(@PathVariable("key") String key ,
         @RequestBody PosjetaEntity posjetaBody, @RequestParam("time") String time){
        if(service.provjeraAdmin(key)){
            int id=0;
            try {
                System.out.println("vrijemeeee "+String.valueOf(time));
                //String des= JSONParser
                LocalTime loc=LocalTime.parse(time);
                Time vrijeme=Time.valueOf(loc);
                System.out.println("vrijemeeee"+vrijeme);
                posjetaBody.setVrijeme(vrijeme);
                System.out.println("Vrijeme posjete: "+posjetaBody.getVrijeme());
                PrezentacijaEntity prez = prezentacija.inputPrezentacija(posjetaBody.getPrezentacijaByPrezentacijaId());
                System.out.println("Prije ubacivanja prezentacije"+ prez.getId());
                id=prez.getId();
                posjetaBody.setPrezentacijaByPrezentacijaId(prez);
                System.out.println("Prije ubacivanja posjete");
                posjeta.inputPosjeta(posjetaBody);
                System.out.println("Poslije posjete");
                return ResponseEntity.ok(prez.getId());
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.ok(id);
            }
        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/{id}/{key}")
    public ResponseEntity<Integer> insertSlike(@PathVariable("key") String key, @PathVariable("id") Integer id, @RequestBody List<String> slike){
        if(service.provjeraAdmin(key)) {
            try{
                PrezentacijaEntity prez=prezentacija.findPrezentacija(id);
                for(String s : slike){
                    SlikaEntity en=new SlikaEntity();
                    en.setPrezentacija(prez);
                    en.setSlika(s);
                    System.out.println("slikeeeeeeeeeeeeeeee");
                    slikaService.putSlika(en);
                }
                return ResponseEntity.ok(1);
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }else return ResponseEntity.badRequest().build();
    }

    @PostMapping("/video/{key}")
    public ResponseEntity<String> putVideo(@PathVariable("key") String key, @RequestParam("file") MultipartFile video)
    {
        if(service.provjeraAdmin(key)) {
            try {
                String ime=video.getOriginalFilename();
                System.out.println(video.getOriginalFilename());
                String ime1=ime.split("\\.")[0];
                String ime2=ime.split("\\.")[1];
                FileUpoladUtil util=new FileUpoladUtil();
                List<String> files = util.getResourceFiles("static");
                Random rand=new Random();
                boolean unique;
                do {
                    unique=true;
                    for (String name : files) {
                        String niz[]=name.split("\\.");

                        if (niz[0].equals(ime1)) {
                            unique=false;
                            break;
                        }
                    }
                    if(!unique){
                        System.out.println("promjena imena");
                        ime1+=rand.nextInt(20);
                    }
                }while(!unique);
                byte[] bytes = video.getBytes();
                String name=ime1+"."+ime2;
                String path=FileUpoladUtil.DIR+"/"+name;
                String path1=FileUpoladUtil.DIR1+"/"+name;
                Files.write(Paths.get(path), bytes);
                Files.write(Paths.get(path1), bytes);
                return ResponseEntity.ok(FileUpoladUtil.dest+name);
            } catch (Exception e) {
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/zavrsena/{id}/{key}")
    public ResponseEntity<Boolean> getZavrsena(@PathVariable("id") int id, @PathVariable("key") String key){
        if(service.provjera(key)) {
            try {
                PosjetaEntity p=posjeta.getPosjetu(id);
                Time vrijeme = p.getVrijeme();
                LocalTime time = vrijeme.toLocalTime();
                Double d = p.getTrajanje() * 60;
                int i = d.intValue();
                time = time.plusMinutes(i);
                LocalTime sada = LocalTime.now();
                if(sada.isAfter(time)){
                    return ResponseEntity.ok(true);
                }
                else return ResponseEntity.ok(false);
            }catch (Exception e){
                e.printStackTrace();
                return ResponseEntity.badRequest().build();
            }
        }else return ResponseEntity.badRequest().build();
    }

    @GetMapping("/sve/{key}")
    public ResponseEntity<List<PosjetaEntity>> getAllPosjete(@PathVariable("key") String key){
        if(service.provjera(key)){
            return ResponseEntity.ok(posjeta.getAll());
        }else return ResponseEntity.badRequest().build();
    }

}
