package com.objectway.stage.examples.mvc.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target({
    ElementType.FIELD
})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = DomainValidator.class)
public @interface Domain {
    Class<? extends Enum<?>> enumClass();
    String message() default "must be any of enum {enumClass}";
    boolean nullable() default false;

    // the additional code is most boilerplate code to conforms to the Spring standards.
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
