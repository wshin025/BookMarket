package kr.ac.kopo.wsk.bookmarket.controller;

import jakarta.servlet.http.HttpServletResponse;
import kr.ac.kopo.wsk.bookmarket.domain.Book;
import kr.ac.kopo.wsk.bookmarket.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Controller
@RequestMapping(value = "/books")
public class BookController {
    @Autowired
    private BookService bookService;

    @Value("${file.uploadDir}")
    String fileDir;

    @GetMapping
    public String requestBookList(Model model) {
        List<Book> bookList = bookService.getAllBookList();
        model.addAttribute("bookList", bookList);
        return "books";
    }

    @GetMapping("/all")
    public ModelAndView requestAllBookList() {
        ModelAndView modelV = new ModelAndView();
        modelV.setViewName("books");
        List<Book> bookList = bookService.getAllBookList();
        modelV.addObject("bookList", bookList);
        return modelV;
    }

    @GetMapping("/book")
    public String requestBookById(@RequestParam("id") String bookId, Model model) {
        Book book = bookService.getBookById(bookId);
        model.addAttribute("book", book);
        return "book";
    }

    @GetMapping("/{category}")
    public String requestBooksByCategory(@PathVariable("category")String category, Model model) {
        List<Book> booksByCategory = bookService.getBookListByCategory(category);
        model.addAttribute("bookList", booksByCategory);
        return "books";
    }

    @GetMapping("/filter/{bookFilter}")
    public String requestBooksByFilter(@MatrixVariable(pathVar = "bookFilter")Map<String, List<String>> bookFilter, Model model) {
        Set<Book> booksByFilter = bookService.getBookListByFilter(bookFilter);
        model.addAttribute("bookList", booksByFilter);
        return "books";
    }
    @GetMapping("/add")
    public String requestAddBookForm() {
        return "addBook";
    }
    @PostMapping("/add")
    public String requestSubmitNewBook(@ModelAttribute("book") Book book) {
        MultipartFile bookImage = book.getBookImage();
        String saveName = bookImage.getOriginalFilename();
        File saveFile = new File(fileDir +saveName);
        if(bookImage != null && !bookImage.isEmpty()) {

        }
        try {
            bookImage.transferTo(saveFile);
        } catch (IOException e) {
            throw new RuntimeException("도서 이미지 업로드가 되지 않았습니다.");
        }
        book.setFileName(saveName);

        bookService.setNewBook(book);
        return "redirect:/books";
    }


    @ModelAttribute
    public void addAttributes(Model model) {
        model.addAttribute("addTitle", "신규 도서 등록");
    }

    @GetMapping("/download")
    public void downloadBookImage(@RequestParam("file") String paramkey, HttpServletResponse response) throws IOException {
        File imageFile = new File(fileDir + paramkey);
        response.setContentType("application/download");
        response.setHeader("Content-Disposition", "attachment; filename=\"" + paramkey + "\"");
        response.setContentLength((int) imageFile.length());
        OutputStream os = response.getOutputStream();
        FileInputStream fis = new FileInputStream(imageFile);
        FileCopyUtils.copy(fis, os);
        fis.close();
        os.close();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {
        binder.setAllowedFields("bookId","name","unitPrice", "author", "description","publisher", "category", "unitsInStock","releaseDate","condition","bookImage");
    }


}
