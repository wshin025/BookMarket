package kr.ac.kopo.wsk.bookmarket.validator;

import jakarta.validation.Constraint;

import java.lang.annotation.*;


@Target({ElementType.METHOD, ElementType.FIELD, ElementType.ANNOTATION_TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = BookIdValidator.class) 
public @interface BookId {
	 String message() default "{BookId.book.bookId}"; 
	   Class<?>[] groups() default {};
	   Class<?>[] payload() default {};  	 
}