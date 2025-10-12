package kr.ac.kopo.wsk.bookmarket.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Book {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bookId;

    private String name;
    private Integer unitPrice;
    private String author;
    private String publisher;
    private String isbn;
    private boolean available = true;

    private String imagePath;
}
