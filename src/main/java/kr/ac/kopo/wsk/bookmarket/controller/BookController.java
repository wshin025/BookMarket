package kr.ac.kopo.wsk.bookmarket.controller;

import kr.ac.kopo.wsk.bookmarket.repository.BookRepository;
import kr.ac.kopo.wsk.bookmarket.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping("/books")
    public String getBooks(Model model) {
        List<Book> bookList = bookRepository.getAllBookList();
        model.addAttribute("bookList", bookList);
        return "books";
    }
}