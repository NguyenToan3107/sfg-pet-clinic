package com.thomas.sfgpetclinic.sfgpetclinic.controller;

import org.springframework.stereotype.Controller;

@Controller
public class MyController {
    public String syaHello()
    {
        System.out.println("hello World");
        return "Hi Thomas";
    }
}
