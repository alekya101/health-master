package com.alekya.application.hc.endpoints.utils;

@FunctionalInterface
public interface IValidate {

    /**
     * Validates request.
     * @return
     */
    Valid validate();
}
