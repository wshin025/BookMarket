package kr.ac.kopo.wsk.bookmarket.repository;

import kr.ac.kopo.wsk.bookmarket.domain.Order;

public interface OrderRepository {
    Long saveOrder(Order order);
}
