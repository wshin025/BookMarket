package kr.ac.kopo.wsk.bookmarket.repository;

import kr.ac.kopo.wsk.bookmarket.domain.Cart;

public interface CartRepository {
    Cart create(Cart cart);

    Cart read(String cartId);

    void update(String cartId, Cart cart);
    void delete(String cartId);//카트안에 전체 항목들을 삭제
}
