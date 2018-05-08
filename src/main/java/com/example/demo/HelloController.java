package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

    @GetMapping(value = "/hello/{name}")
    public String greet(@PathVariable String name) {
        return "Hello " + name;
    }

    @PostMapping(value = "/hello")
    public String greetAsPost(@RequestBody String name) {
        return "Hola " + name;
    }
}
