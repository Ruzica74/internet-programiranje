package com.example.muzej.service;

import com.example.muzej.model.VideoAppEntity;
import com.example.muzej.repository.VideoAppRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VideoAppService {

    @Autowired
    VideoAppRepository rep;

    public VideoAppEntity getVideo(int id){
        return rep.findById(id).get();
    }

}
