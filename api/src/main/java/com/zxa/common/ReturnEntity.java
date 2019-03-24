package com.zxa.common;

import lombok.Getter;

@Getter
public class ReturnEntity {
    private int code;
    private String message;
    private Object data;

    private static final ReturnEntity Success_Instance = new ReturnEntity(ApplicationConstant.OK);


    public static ReturnEntity error(ApplicationConstant applicationConstant) {
        return new ReturnEntity(applicationConstant);
    }

    public static ReturnEntity success() {
        return Success_Instance;
    }

    public static ReturnEntity success(Object data) {
        return new ReturnEntity(ApplicationConstant.OK, data);
    }

    private ReturnEntity(ApplicationConstant applicationConstant) {
        this.code = applicationConstant.getCode();
        this.message = applicationConstant.getMessage();
    }

    private ReturnEntity(ApplicationConstant applicationConstant, Object data) {
        this(applicationConstant);
        this.data = data;
    }
}
