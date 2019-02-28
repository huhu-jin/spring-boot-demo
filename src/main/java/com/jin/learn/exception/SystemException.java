package com.jin.learn.exception;


public class SystemException extends RuntimeException {
    private static final long serialVersionUID = 5540484171361000892L;

    private ExceptionCode exceptionCode;

    public SystemException() {
        super();
        exceptionCode = ExceptionCode.SYSTEM_ERROR;
    }

    public SystemException(String message) {
        super(message);
        exceptionCode = ExceptionCode.SYSTEM_ERROR;
    }


    public SystemException(ExceptionCode exceptionCode) {
        super();
        this.exceptionCode = exceptionCode;
    }


    public ExceptionCode getExceptionCode() {
        return exceptionCode;
    }


}
