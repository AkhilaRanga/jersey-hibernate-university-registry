package com.learn.javaweb.util;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

public class ValidatorUtils {
    private static final ValidatorFactory validatorFactory;
    private static final Validator validator;

    static {
        ValidatorFactory tempFactory = null;
        Validator tempValidator = null;
        try {
            tempFactory = Validation.buildDefaultValidatorFactory();
            tempValidator = tempFactory.getValidator();
        } catch (Exception e) {
            System.err.println("Failed to create Validator: " + e);
            e.printStackTrace();
            throw new ExceptionInInitializerError(e);
        }
        validatorFactory = tempFactory;
        validator = tempValidator;
    }

    public static Validator getValidator() {
        return validator;
    }

    public static void close() {
        if (validatorFactory != null) {
            validatorFactory.close();
        }
    }
}
