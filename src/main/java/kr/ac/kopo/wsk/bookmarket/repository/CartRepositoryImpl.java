package kr.ac.kopo.wsk.bookmarket.repository;

import kr.ac.kopo.wsk.bookmarket.domain.Cart;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepositoryImpl implements CartRepository {
    private Map<String, Cart> ListOfCarts;

    public CartRepositoryImpl(){
        ListOfCarts = new HashMap<String, Cart>();
    }

    @Override
    public Cart create(Cart cart){
        if(ListOfCarts.containsKey(cart.getCartId())){
        throw new IllegalArgumentException("Cart already exists");
        }
        ListOfCarts.put(cart.getCartId(), cart);
        return null;
        }

    @Override
    public Cart read(String cartId){
        return null;
    }

}
