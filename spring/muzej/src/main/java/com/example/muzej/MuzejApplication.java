package com.example.muzej;

import com.example.muzej.other.EmailThread;
import com.example.muzej.other.FileUpoladUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
public class MuzejApplication {



    public static void main(String[] args) {
        SpringApplication.run(MuzejApplication.class, args);

        //emailThread.start();
    }


}
