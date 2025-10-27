package kr.ac.kopo.wsk.bookmarket.controller;

import kr.ac.kopo.wsk.bookmarket.domain.*;
import kr.ac.kopo.wsk.bookmarket.service.*;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.List;

    @Controller
    @RequestMapping("/BookMarket/order")
    public class OrderController {

        private final CartService cartService;
        private final BookService bookService;
        private final OrderService orderService;
        private final OrderProService orderProService; // ✅ 유지

        public OrderController(CartService cartService, BookService bookService,
                               OrderService orderService, OrderProService orderProService) {
            this.cartService = cartService;
            this.bookService = bookService;
            this.orderService = orderService;
            this.orderProService = orderProService;
        }

        @GetMapping("/{cartId}")
        public String startOrder(@PathVariable String cartId, HttpSession session, Model model) {
            Cart cart = cartService.read(cartId);

            Order order = new Order();
            List<Book> listOfBooks = new ArrayList<>();
            cart.getCartItems().forEach((bookId, item) -> {
                Book b = item.getBook();
                listOfBooks.add(b);
                OrderItem oi = new OrderItem();
                oi.setBookId(b.getBookId());
                oi.setQuantity(item.getQuantity());
                oi.setTotalPrice(item.getTotalPrice());
                order.getOrderItems().put(b.getBookId(), oi);
            });
            order.setGrandTotal(cart.getGrandTotal());

            session.setAttribute("order", order);
            model.addAttribute("customer", order.getCustomer());
            model.addAttribute("bookList", listOfBooks);
            return "orderCustomerInfo";
        }

        @PostMapping("/orderCustomerInfo")
        public String postCustomer(@Valid @ModelAttribute("customer") Customer customer,
                                   BindingResult bindingResult,
                                   HttpSession session, Model model) {
            if (bindingResult.hasErrors()) return "orderCustomerInfo";

            Order order = (Order) session.getAttribute("order");
            order.setCustomer(customer);

            model.addAttribute("shipping", order.getShipping());
            return "orderShippingInfo";
        }

        @PostMapping("/orderShippingInfo")
        public String postShipping(@Valid @ModelAttribute("shipping") Shipping shipping,
                                   BindingResult bindingResult,
                                   HttpSession session, Model model) {
            if (bindingResult.hasErrors()) return "orderShippingInfo";

            Order order = (Order) session.getAttribute("order");
            order.setShipping(shipping);

            model.addAttribute("order", order);
            model.addAttribute("customer", order.getCustomer());
            model.addAttribute("shipping", order.getShipping());
            model.addAttribute("bookList", new ArrayList<Book>());
            return "orderConfirmation";
        }

        @PostMapping("/orderConfirmation")
        public String confirm(HttpSession session, Model model) {
            Order order = (Order) session.getAttribute("order");
            orderService.saveOrder(order);

            model.addAttribute("order", order);
            model.addAttribute("customer", order.getCustomer());
            model.addAttribute("shipping", order.getShipping());

            session.removeAttribute("order");
            return "orderFinished";
        }

        @PostMapping("/orderCancelled")
        public String cancel(HttpSession session, Model model) {
            session.removeAttribute("order");
            model.addAttribute("message", "주문이 취소되었습니다.");
            return "orderCancelled";
        }

        @GetMapping("/list")
        public String viewHomePage(Model model) {
            return viewPage(1, "orderId", "asc", model);
        }

        @GetMapping("/page")
        public String viewPage(@RequestParam("pageNum") int pageNum,
                               @RequestParam("sortField") String sortField,
                               @RequestParam("sortDir") String sortDir,
                               Model model) {

            Page<Order> page = orderProService.listAll(pageNum, sortField, sortDir);
            List<Order> orders = page.getContent();

            model.addAttribute("currentPage", pageNum);
            model.addAttribute("totalPages", page.getTotalPages());
            model.addAttribute("totalItems", page.getTotalElements());
            model.addAttribute("sortField", sortField);
            model.addAttribute("sortDir", sortDir);
            model.addAttribute("reverseSortDir", sortDir.equals("asc") ? "desc" : "asc");
            model.addAttribute("orderList", orders);

            return "orderList";
        }

        @GetMapping("/view/{id}")
        public ModelAndView viewOrder(@PathVariable("id") Long id) {
            Order order = orderProService.get(id);
            List<Book> listOfBooks = new ArrayList<>();

            for (OrderItem orderItem : order.getOrderItems().values()) {
                String bookId = orderItem.getBookId();
                Book book = bookService.getBookById(bookId);
                listOfBooks.add(book);
            }

            ModelAndView modelAndView = new ModelAndView("orderView");
            modelAndView.addObject("order", order);
            modelAndView.addObject("bookList", listOfBooks);
            return modelAndView;
        }
    }


