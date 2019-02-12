package com.zxa.entitis;

import lombok.Data;

import java.time.LocalDate;

@Data
public class Parameter {
    private LocalDate now;
    private String message;
}
