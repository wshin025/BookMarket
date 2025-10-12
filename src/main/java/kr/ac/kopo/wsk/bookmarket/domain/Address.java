package kr.ac.kopo.wsk.bookmarket.domain;

import jakarta.persistence.Embeddable;
import lombok.*;

@Embeddable
@Getter @Setter @NoArgsConstructor @AllArgsConstructor @Builder
public class Address {
    private String country;
    private String zipcode;
    private String addressName;
    private String detailName;
}
