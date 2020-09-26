package com.alekya.application.hc.beans;

import com.openpojo.validation.ValidatorBuilder;
import com.openpojo.validation.test.impl.GetterTester;
import com.openpojo.validation.test.impl.SetterTester;
import org.junit.jupiter.api.Test;

public class PojoCommonTest {

    private static final String PACKAGE = "com.alekya.application.hc.beans";

    @Test
    public void testAllBeans() {
        ValidatorBuilder.create()
                .with(new GetterTester())
                .with(new SetterTester())
                .build()
                .validate(PACKAGE);
    }
}
