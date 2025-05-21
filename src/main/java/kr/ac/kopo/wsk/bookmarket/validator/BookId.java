package kr.ac.kopo.wsk.bookmarket.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = BookIdValidator.class)
@Target({ElementType.METHOD, ElementType.FIELD,ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)

public @interface BookId {
    String message() default "{BookId.book.bookId}";
    Class<?>[] groups() default {};
    Class<?>[] payload() default {};
}
