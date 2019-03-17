package com.zxa.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginPojo {
    private String userName;
    private String sessionId;
}
