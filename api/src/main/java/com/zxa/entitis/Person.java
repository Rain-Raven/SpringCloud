package com.zxa.entitis;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class Person {
    private String name;
    private Long id;
    private Integer age;
    private LocalDate birthday;
    private LocalTime lastLoginTime;
    private LocalDateTime createTime;
}
