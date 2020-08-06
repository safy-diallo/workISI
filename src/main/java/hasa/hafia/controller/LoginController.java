package hasa.hafia.controller;

import hasa.hafia.entites.LoginRequest;
import hasa.hafia.entites.Roles;
import hasa.hafia.entites.Users;
import hasa.hafia.service.LoginService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Optional;

@Controller
@RequestMapping("/login")
public class LoginController {
    private final LoginService service;
    private static final String DEFAULT_VIEW = "views/login";

    public LoginController(LoginService service) {
        this.service = service;
    }

    @GetMapping
    public String index(Model model){
        model.addAttribute("request", new LoginRequest());
        return DEFAULT_VIEW.concat("/login");
    }

    @PostMapping
    public void login(@ModelAttribute("request") LoginRequest request, Model model){
        Users users = service.login(request);
        model.addAttribute("user", users);
    }
}
