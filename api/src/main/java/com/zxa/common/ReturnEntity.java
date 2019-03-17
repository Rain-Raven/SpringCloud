package com.zxa.common;

import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ReturnEntity {
    private int code;
    private String message;
    private Object data;


    public static ReturnEntity error(ApplicationConstant applicationConstant){
        return new ReturnEntity(applicationConstant);
    }

    public static ReturnEntity success(){
        return new ReturnEntity(ApplicationConstant.OK);
    }

    public static ReturnEntity success(Object data){
        return new ReturnEntity(ApplicationConstant.OK).setData(data);
    }

    private ReturnEntity(ApplicationConstant applicationConstant){
        this.code=applicationConstant.getCode();
        this.message=applicationConstant.getMessage();
    }
}
