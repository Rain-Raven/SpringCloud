package com.zxa.parameter;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class EmailAddress {
    @NotEmpty
    @Email
    private String email;
}
