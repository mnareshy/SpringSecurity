package com.springsecurity.services.we;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloService {

    @GetMapping("/name")
    public String sayMyName(){

        System.out.println("**************************************************");

        return "Sairam";
    }

    @GetMapping("/mrb")
    public String hayMrB(){


        return "No New Reviews Buddy";
    }
}
