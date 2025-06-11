package kr.ac.kopo.wsk.bookmarket.domain;

import lombok.Data;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@Data
@ToString
public class Cart {
    private String cartId;
    private Map <String, Cartitem> cartItems;
    private BigDecimal grandTotal;


    public Cart() {
        cartItems = new HashMap<String, Cartitem>();
        grandTotal = new BigDecimal(0);
    }
    public Cart(String cartId){
        this();
        this.cartId = cartId;
    }
}
