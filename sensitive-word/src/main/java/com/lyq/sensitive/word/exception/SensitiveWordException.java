package com.lyq.sensitive.word.exception;

/**
 * @author Emcikem
 * @create 2022/4/29
 * @desc 统一异常
 */
public class SensitiveWordException extends RuntimeException {

    public SensitiveWordException(String message) {
        super(message);
    }

    public SensitiveWordException(String message, Throwable cause) {
        super(message, cause);
    }
}
