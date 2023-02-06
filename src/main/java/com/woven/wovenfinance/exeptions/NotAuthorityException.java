package com.woven.wovenfinance.exeptions;

public class NotAuthorityException extends RuntimeException {
    public NotAuthorityException(String s) {
        super(s);
    }
}
