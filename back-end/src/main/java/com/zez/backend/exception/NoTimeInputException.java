package com.zez.backend.exception;

/**
 * @Author Purple
 * @Date 2020/8/14
 */

public class NoTimeInputException extends Exception {

    public NoTimeInputException(){
    }

    public NoTimeInputException(String message){
        super(message);
    }

    public NoTimeInputException(String message, Throwable cause){
        super(message, cause);
    }

    public NoTimeInputException(Throwable cause){
        super(cause);
    }

    public NoTimeInputException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace){
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
