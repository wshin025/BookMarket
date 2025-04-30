package kr.ac.kopo.wsk.bookmarket.controller;

import kr.ac.kopo.wsk.bookmarket.repository.BookRepository;
import kr.ac.kopo.wsk.bookmarket.domain.Book;
import kr.ac.kopo.wsk.bookmarket.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/books")
public class BookController {

    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private BookService bookService;

    @GetMapping
    public String requestBookList(Model model) {
        List<Book> bookList = bookRepository.getAllBookList();
        model.addAttribute("bookList", bookList);
        return "books";
    }

//    @GetMapping("/all")
//    public String requestAllBookList(Model model) {
//        List<Book> bookList = bookRepository.getAllBookList();
//        model.addAttribute("bookList", bookList);
//        return "books";
//    }
//    @GetMapping("/all")
//    public String requestAllBookList() {
//        ModelAndView modelv = new ModelAndView();
//        modelv.setViewName("books");
//        List<Book> bookList = bookRepository.getAllBookList();
//        modelv.addObject("bookList", bookList);
//        return "Modelv";
//    }
    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId, Model model ) {
        Book book = bookService.getBookById(bookId);
        System.out.println(book.getName());
        model.addAttribute("book", book);
        return "book";
    }
    @GetMapping("/{category}")
    public String requestBooksByCategory(@PathVariable("category") String category, Model model) {
        List<Book> booksByCategory = bookService.getBookListByCategory(category);
        model.addAttribute("bookList", booksByCategory);
        return "books";
    }
    @GetMapping("/filter/{bookFilter}")
    public String requestBooksByFilter(@MatrixVariable(pathVar = "bookFilter") Map<String, List<String>> bookFilter , Model model) {
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);

        return "books";
    }
}
