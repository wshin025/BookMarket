package kr.ac.kopo.wsk.bookmarket.repository;

import kr.ac.kopo.wsk.bookmarket.domain.Order;

public interface OrderRepository {
//    주문목록 저장
    Long saveOrder(Order order);
}
