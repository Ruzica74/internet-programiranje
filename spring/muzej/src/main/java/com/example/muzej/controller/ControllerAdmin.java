package com.example.muzej.controller;

import com.example.muzej.model.AdminEntity;
import com.example.muzej.service.AdminService;
import com.example.muzej.service.KorisnikService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*")
@RequestMapping("/admin")
@RestController
public class ControllerAdmin {
    @Autowired
    AdminService service;

    @Autowired
    KorisnikService serv;

    @GetMapping("/{id}/{key}")
    public ResponseEntity<AdminEntity> getAdmin(@PathVariable("id") int id, @PathVariable("key") String key){
        if(serv.provjera(key)){
            AdminEntity a=service.getAdmin(id);
            return ResponseEntity.ok(a);
        }else return ResponseEntity.badRequest().build();
    }
}
