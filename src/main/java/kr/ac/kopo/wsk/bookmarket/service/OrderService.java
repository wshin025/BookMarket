package kr.ac.kopo.wsk.bookmarket.service;

import kr.ac.kopo.wsk.bookmarket.domain.Order;

public interface OrderService {
    void confirmOrder(String bookId, long quantity); // 도서 재고 수량 확인
    long saveOrder(Order order);
}
