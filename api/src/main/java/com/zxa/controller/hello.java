package com.zxa.controller;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.zxa.entitis.Parameter;
import com.zxa.entitis.Person;
import com.zxa.entitis.Student;
import com.zxa.utils.RandomStringUtil;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.logging.SimpleFormatter;

@RestController
public class hello {
    private final RandomStringUtil randomStringUtil = RandomStringUtil.builder().letters().numbers().create();

    @RequestMapping(value = "/hello",method = {RequestMethod.POST,RequestMethod.GET})
    public Student hello(Parameter date) throws ParseException {
        Person person = new Person();
//        person.setId(Long.valueOf(1));
        person.setName(randomStringUtil.getRandomString(6));
        person.setAge(1);
        person.setLastLoginTime(LocalTime.now());
        person.setCreateTime(LocalDateTime.now());
        person.setBirthday(LocalDate.now());
        Student student=new Student();
        student.setPerson(person);
        student.setNow(date.getNow());
        student.setClassNumber(date.getMessage());
        student.setStuId(1L);
        return student;
    }
}
