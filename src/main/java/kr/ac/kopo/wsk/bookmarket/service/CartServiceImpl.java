package kr.ac.kopo.wsk.bookmarket.service;

import org.springframework.stereotype.Service;
import kr.ac.kopo.wsk.bookmarket.domain.Cart;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

@Service
public class CartServiceImpl implements CartService {

    // 메모리 기반 장바구니 저장소
    private final Map<String, Cart> cartStore = new ConcurrentHashMap<>();

    @Override
    public Cart create(Cart cart) {
        return null;
    }

    @Override
    public Cart read(String cartId) {
        return cartStore.get(cartId);
    }

    @Override
    public void save(Cart cart) {
        cartStore.put(cart.getCartId(), cart);
    }

    @Override
    public void update(String cartId, Cart cart) {

    }

    @Override
    public void delete(String cartId) {
        cartStore.remove(cartId);
    }
}
