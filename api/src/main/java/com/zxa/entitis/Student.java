package com.zxa.entitis;

import lombok.Data;

import java.util.Date;

@Data
public class Student {
    private Person person;
    private Long stuId;
    private String classNumber;
    private Date now;
}
