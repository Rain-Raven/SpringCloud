package com.zxa.parameter;

import lombok.Data;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

@Data
public class RegisterParameter {
    @Email
    private String email;
    @NotEmpty
    @Length(min = 4,max = 20)
    private String userName;
    @NotEmpty
    @Length(min = 6,max = 20)
    private String password;
    @NotEmpty
    @Length(min = 6,max = 6)
    private String captcha;
}
