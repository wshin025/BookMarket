package kr.ac.kopo.wsk.bookmarket.domain;

import jakarta.validation.constraints.*;
import kr.ac.kopo.wsk.bookmarket.validator.BookId;
import lombok.*;
import org.springframework.web.multipart.MultipartFile;

import java.math.BigDecimal;

@Data
//@Getter
//@Setter
//@NoArgsConstructor
public class Book {
    @BookId
    @Pattern(regexp = "isbn[0-9]+", message = "{Pattern.book.bookId}")
    private String bookId; //도서번호
    @Size(min = 4, max = 50, message = "{Size.book.name}")
    private String name; //도서명
    @Min(value = 0, message = "{Min.book.unitPrice}")
    @Digits(integer = 8, fraction = 2, message = "{Digits.book.unitPrice}")
    @NotNull(message = "{NotNull.book.unitPrice}")
    private BigDecimal unitPrice; //단가
    private String author; //저자
    private String description; //도서설명
    private String publisher; //출판사
    private String category; //도서분류
    private long unitsInStock; //재고량
    private String releaseDate; //출판일
    private String condition; //신규도서 or 중고도서 or 전자책
    private String fileName; //도서 이미지 파일
    private MultipartFile bookImage; //업로드된 도서 이미지 파일
}
