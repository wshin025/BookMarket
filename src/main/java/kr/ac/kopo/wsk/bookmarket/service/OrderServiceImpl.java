package kr.ac.kopo.wsk.bookmarket.service;

import kr.ac.kopo.wsk.bookmarket.domain.Book;
import kr.ac.kopo.wsk.bookmarket.domain.Order;
import kr.ac.kopo.wsk.bookmarket.repository.BookRepository;
import kr.ac.kopo.wsk.bookmarket.repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public void confirmOrder(String bookId, long quantity) {
     Book bookById = bookRepository.getBookById(bookId);
     if(bookById.getUnitsInStock() < quantity) {
         throw  new IllegalArgumentException("도서수량이 부족합니다. 구입가능한 도서 수량은 : " + bookById.getUnitsInStock());
     }
     bookById.setUnitsInStock(bookById.getUnitsInStock() - quantity);
    }

    @Override
    public long saveOrder(Order order) {
        Long orderId = orderRepository.saveOrder(order);
        return 0L;
    }
}
