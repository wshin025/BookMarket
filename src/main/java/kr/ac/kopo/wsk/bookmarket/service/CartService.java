package kr.ac.kopo.wsk.bookmarket.service;

import kr.ac.kopo.wsk.bookmarket.domain.Cart;

public interface CartService {
    Cart create(Cart cart);
    Cart read(String cartId);
    void update(String cartId, Cart cart);
    void delete(String cartId);
}
