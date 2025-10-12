package kr.ac.kopo.wsk.bookmarket.domain;

import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity @Table(name = "orders")
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Order {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(cascade = CascadeType.ALL)
    private Customer customer;

    @OneToOne(cascade = CascadeType.ALL)
    private Shipping shipping;

    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> orderItems = new ArrayList<>();

    private int grandTotal;

    public void addItem(Book b, int qty) {
        int price = (b.getUnitPrice() == null ? 0 : b.getUnitPrice()) * qty;
        orderItems.add(OrderItem.builder().book(b).quantity(qty).totalPrice(price).build());
        grandTotal += price;
    }
}
