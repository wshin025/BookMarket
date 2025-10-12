package kr.ac.kopo.wsk.bookmarket.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import kr.ac.kopo.wsk.bookmarket.domain.Cart;


public interface CartRepository {
    Cart create(Cart cart);

    Cart read(String cartId);

    Cart create(String cartId, Cart cart);

    void update(String cartId, Cart cart);
    void delete(String cartId);//카트안에 전체 항목들을 삭제
}

