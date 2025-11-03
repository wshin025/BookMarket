package kr.ac.kopo.wsk.bookmarket.validator;

import kr.ac.kopo.wsk.bookmarket.domain.Book;
import kr.ac.kopo.wsk.bookmarket.service.BookService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;

public class BookIdValidator implements ConstraintValidator<BookId, String>{

	@Autowired
	private BookService bookService;
	   
	
	@Override
	public void initialize(BookId constraintAnnotation) {
		// TODO Auto-generated method stub
		ConstraintValidator.super.initialize(constraintAnnotation);
	}

	@Override
	public boolean isValid(String value, ConstraintValidatorContext context) {
		  Book book;
	      try {
	         book = bookService.getBookById(value);
	      } catch (RuntimeException e) {	    	  
	         return true;
	      }
	      if(book!= null) {
	         return false;
	      }
	      return true;	   
	}
}