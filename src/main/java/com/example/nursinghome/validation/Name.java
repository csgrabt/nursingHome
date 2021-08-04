package com.example.nursinghome.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD})
@Constraint(validatedBy = NameValidator.class)
public @interface Name {

    String message() default "Name cannot be null, empty, blank, have to has space, the part of the names has to start with Capital letter, just alphabetic character can be used";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int maxLength() default 50;

}
