package com.zxa.consumers.controller;

import com.zxa.entitis.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class HelloWorld {

    @Autowired
    RestTemplate restTemplate;
    @RequestMapping(value = "/la",method = {RequestMethod.GET,RequestMethod.POST})
    public Person helloWorld(){
        Person person=new Person();
        person.setName("邹鑫安");
        person.setAge(22);
        person.setId(1L);
        return person;
    }
}
