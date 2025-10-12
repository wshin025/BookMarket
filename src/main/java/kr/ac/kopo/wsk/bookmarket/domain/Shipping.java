package kr.ac.kopo.wsk.bookmarket.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Shipping {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name; // 수령인
    private String date; // yyyy/MM/dd

    @Embedded
    private Address address;
}
