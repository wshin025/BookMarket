package kr.ac.kopo.wsk.bookmarket.repository;

import kr.ac.kopo.wsk.bookmarket.domain.Order;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository {
    private Map<Long,Order>listoforders;
    private long nextOrderId;

    public OrderRepositoryImpl() {
        listoforders = new HashMap<>();
        nextOrderId = 2000;
    }


    @Override
    public Long saveOrder(Order order){
        order.setOrderid(getNextOrderId());
        listoforders.put(order.getOrderid(),order);
        return order.getOrderid();
    }

    private synchronized long getNextOrderId() {
        return nextOrderId++;
    }
}
