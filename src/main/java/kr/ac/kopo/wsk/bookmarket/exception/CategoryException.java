package kr.ac.kopo.wsk.bookmarket.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value=HttpStatus.NOT_FOUND)
public class CategoryException extends RuntimeException {
private String errorMessage;
private String category;
public CategoryException(String category) {
    super();
    this.category = category;
    errorMessage = "요청한 도서 분야를 찾을 수 없습니다.";
    System.out.println(errorMessage);
    System.out.println(category);
}

    public String getErrorMessage() {
        return errorMessage;
    }

    public String getCategory() {
        return category;
    }
}
