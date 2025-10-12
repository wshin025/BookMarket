package kr.ac.kopo.wsk.bookmarket.domain;

import lombok.*;

import java.util.LinkedHashMap;
import java.util.Map;

@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Cart {
    private String cartId;
    private Map<Long, CartItem> cartItems = new LinkedHashMap<>();

    public int getGrandTotal() {
        return cartItems.values().stream().mapToInt(CartItem::getTotalPrice).sum();
    }

    public void addBook(Book book, int quantity) {
        var item = cartItems.getOrDefault(book.getBookId(),
                CartItem.builder().book(book).quantity(0).build());
        item.setQuantity(item.getQuantity() + quantity);
        cartItems.put(book.getBookId(), item);
    }

    public void removeBook(Long bookId) {
        cartItems.remove(bookId);
    }

    public void clear() { cartItems.clear(); }
}
