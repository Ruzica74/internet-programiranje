package com.example.muzej.controller;

import com.example.muzej.model.VideoAppEntity;
import com.example.muzej.service.VideoAppService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/videoApp")
public class ControllerVideoApp {

    @Autowired
    VideoAppService service;

    @GetMapping("/{id}")
    public ResponseEntity<VideoAppEntity> getVideo(@PathVariable("id") int id){
        return ResponseEntity.ok(service.getVideo(id));
    }
}
