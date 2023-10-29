package com.monitorexample.hashedwheeltimerdemo.requst;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class DurationValidator implements ConstraintValidator<DurationConstraint, Long> {

    @Override
    public boolean isValid(Long duration, ConstraintValidatorContext constraintValidatorContext) {
        return duration >= 600L && duration <= 36000;
    }
}
