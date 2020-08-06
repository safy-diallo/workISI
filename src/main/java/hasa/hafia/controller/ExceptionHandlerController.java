package hasa.hafia.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/error")
public class ExceptionHandlerController {

    @GetMapping("/unauthorized")
    public String unauthorized(){
        return "errors/unauthorized";
    }
}
