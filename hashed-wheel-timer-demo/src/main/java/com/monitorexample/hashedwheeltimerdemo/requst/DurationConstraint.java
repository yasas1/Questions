package com.monitorexample.hashedwheeltimerdemo.requst;

import javax.validation.Constraint;
import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = {DurationValidator.class})
@Target({ElementType.METHOD, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DurationConstraint {
    String message() default "Duration should be between 10 minutes and 10 hours.";
}
