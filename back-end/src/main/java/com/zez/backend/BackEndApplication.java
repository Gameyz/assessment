package com.zez.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BackEndApplication {

    public static void main(String[] args){
        System.setProperty("es.set.netty.runtime.available.processors", "false");
        SpringApplication.run(BackEndApplication.class, args);
    }

}
