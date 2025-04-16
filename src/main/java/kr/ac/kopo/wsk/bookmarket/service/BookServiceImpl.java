package kr.ac.kopo.wsk.bookmarket.service;

import kr.ac.kopo.wsk.bookmarket.domain.Book;
import kr.ac.kopo.wsk.bookmarket.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    @Autowired
    private BookRepository bookRepository ;
    @Autowired
    private BookService bookService;

    @Override
    public List<Book> getAllBookList(){
        return bookRepository.getAllBookList();
    }

    @Override
    public Book getBookById(String bookId) {
        Book book = bookRepository.getBookById(bookId);
        return book;
    }
    @Override
    public List<Book> getBookListByCategory(String category){
        List<Book> booksByCategory = bookRepository.getBookListByCategory(category);

        return booksByCategory;

    }

}
