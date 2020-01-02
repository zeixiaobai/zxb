package org.zxb.web.annotation;

import org.zxb.web.validate.MyValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE, ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { MyValidator.class })
public @interface My {

    String message() default "{params.error}";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
