package com.zez.backend.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author Purple
 * @Date 2020/7/18
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommonResult<T> {
    private Integer code;
    private String message;
    private T data;

    public CommonResult<T> success(String message, T data){
        this.setCode(200);
        this.setMessage(message);
        this.setData(data);
        return this;
    }

    public static CommonResult<Object> succ(String message,Object data){
        return new CommonResult<>(200,message,data);
    }
    public static CommonResult<Object> fail(Integer code,String message){
        return new CommonResult<Object> (code,message,null);
    }
}
