package com.example.demo;

import org.springframework.stereotype.Component;

@Component
public class Chess implements Game{
    @Override
    public void play() {
       System.out.println("chess");
    }
}
