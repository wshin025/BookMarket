package kr.ac.kopo.wsk.bookmarket.controller;

import jakarta.servlet.http.HttpServletRequest;
import kr.ac.kopo.wsk.bookmarket.domain.Cart;
import kr.ac.kopo.wsk.bookmarket.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/cart")
public class CartController {
    @Autowired
    private CartService cartService;

    @GetMapping
    public String requestCartId(HttpServletRequest request) {
        System.out.println("Call requestCartId()");
        String sessionId = request.getSession().getId();
        return "redirect:/cart/" + sessionId;
    }

    @PostMapping
    public @ResponseBody Cart create(@RequestBody Cart cart) {
        System.out.println("Call create()");
        return cartService.create(cart);
    }

    @GetMapping("/{cartId}")
    public String requestCartList(@PathVariable String cartId, Model model) {
        System.out.println("Call requestCartList()");
        Cart cart = cartService.read(cartId);
        model.addAttribute("cart", cart);
        return "cart";
    }

    @PostMapping("/{cartId}")
    public @ResponseBody Cart read(@PathVariable("cartId")String cartId ) {
        System.out.println("Call read()");
        return cartService.read(cartId);
    }
}
