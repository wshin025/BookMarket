package kr.ac.kopo.wsk.bookmarket.controller;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }
    @GetMapping("/loginfailed")
    public String loginfailed(Model model) {
        model.addAttribute("error", "true");
        return "login";
    }
}
