package hasa.hafia.controller;

import hasa.hafia.entites.Users;
import hasa.hafia.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/users")
public class UserController {
    private  UserService service;
    private static final String DEFAULT_VIEW = "views/users/index";

   
    
    @GetMapping
    public String home(){
        //todo...
        return DEFAULT_VIEW;
    }

    @PostMapping
    public String create(Users users){
        Users saved = service.create(users);
        //todo...
        return DEFAULT_VIEW;
    }

    @PutMapping("/{id}")
    public String update(@PathVariable Long id, Users users){
        Users updated = service.update(id, users);
        //todo...
        return DEFAULT_VIEW;
    }

    @GetMapping("/{id}")
    public String get(@PathVariable Long id){
        Users found = service.findById(id);
        //todo...
        return DEFAULT_VIEW;
    }

    @GetMapping("/list")
    public String getAll(){
        List<Users> users = service.getAll();
        //todo...
        return DEFAULT_VIEW;
    }

    @DeleteMapping("/{id}")
    public String delete(@PathVariable Long id){
        service.delete(id);
        //todo...
        return DEFAULT_VIEW;
    }

    @Autowired
    public void setService(UserService service) {
        this.service = service;
    }
}
