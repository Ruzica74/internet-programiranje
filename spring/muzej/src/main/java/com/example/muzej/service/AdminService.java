package com.example.muzej.service;

import com.example.muzej.model.AdminEntity;
import com.example.muzej.repository.AdminRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    AdminRepository repository;

    public AdminEntity getAdmin(int id){
        AdminEntity a;
        try {
            a = repository.findById(id).get();
        }catch(Exception e){
            a=null;
        }
        return a;
    }
}
