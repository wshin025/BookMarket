package kr.ac.kopo.wsk.bookmarket.domain;

import lombok.*;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class CartItem {
    private Book book;
    private int quantity;

    public int getTotalPrice() {
        return (book == null || book.getUnitPrice() == null) ? 0 : book.getUnitPrice() * quantity;
    }
}
