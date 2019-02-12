package com.zxa.controller;

import com.zxa.entitis.Person;
import com.zxa.entitis.Student;
import com.zxa.utils.RandomStringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@RestController
public class hello {
    private final RandomStringUtil randomStringUtil = RandomStringUtil.builder().letters().numbers().create();

    @GetMapping(value = "/hello")
    public Student hello(){
        Person person = new Person();
//        person.setId(Long.valueOf(1));
        person.setName(randomStringUtil.getRandomString(6));
        person.setAge(1);
        person.setLastLoginTime(LocalTime.now());
        person.setCreateTime(LocalDateTime.now());
        person.setBirthday(LocalDate.now());
        Student student=new Student();
        student.setPerson(person);
        student.setNow(new Date());
        student.setClassNumber(randomStringUtil.getRandomString(3));
        student.setStuId(1L);
        return student;
    }
}
