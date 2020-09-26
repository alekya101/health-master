package com.alekya.application.hc.endpoints.utils;

public interface ICanMapToBean<BEAN> {

    /**
     * Converts request to entity
     * @return  BEAN
     */
    BEAN toBean();
}
