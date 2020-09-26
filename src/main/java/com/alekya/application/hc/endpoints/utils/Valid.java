package com.alekya.application.hc.endpoints.utils;

import lombok.Getter;

@Getter
public class Valid {

    private boolean isValid;

    private String message;

    private Valid() {
        isValid = Boolean.TRUE;
    }

    private Valid(String message) {
        isValid = Boolean.FALSE;
        this.message = message;
    }

    public static Valid valid() {
        return new Valid();
    }

    public static Valid invalid(String message) {
        return new Valid(message);
    }
}
