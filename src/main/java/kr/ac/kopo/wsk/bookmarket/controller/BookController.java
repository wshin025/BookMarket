package kr.ac.kopo.wsk.bookmarket.controller;

import kr.ac.kopo.wsk.bookmarket.repository.BookRepository;
import kr.ac.kopo.wsk.bookmarket.domain.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;

    @GetMapping
    public String requestBookList(Model model) {
        List<Book> bookList = bookRepository.getAllBookList();
        model.addAttribute("bookList", bookList);
        return "books";
    }

    @GetMapping("/all")
    public String requestAllBookList(Model model) {
        List<Book> bookList = bookRepository.getAllBookList();
        model.addAttribute("bookList", bookList);
        return "books";
    }
    @GetMapping("/all")
    public String requestAllBookList() {
        ModelAndView modelv = new ModelAndView();
        modelv.setViewName("books");
        List<Book> bookList = bookRepository.getAllBookList();
        modelv.addObject("bookList", bookList);
        return "Modelv";
    }
}
