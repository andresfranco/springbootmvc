package com.andresfranco.testspring.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import com.andresfranco.testspring.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import com.andresfranco.testspring.model.User;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class UserController extends GeneralController {

    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @RequestMapping("/users")
    public String getUsers(Model model) {

        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("showRoute", this.setRoute("users", "show", ""));
        model.addAttribute("editRoute", this.setRoute("users", "edit", ""));
        model.addAttribute("newRoute", this.setRoute("users", "new", ""));
        model.addAttribute("deleteRoute", this.setRoute("users", "delete", ""));
        return "users/index";
    }

    @RequestMapping("/users/show/{userId}")
    public String ShowUser(@PathVariable String userId, Model model) {

        model.addAttribute("user", userRepository.findById(new Long(userId)).get());
        model.addAttribute("indexRoute", this.setRoute("users", "index", ""));
        return "users/show";
    }

    @RequestMapping("/users/edit/{userId}")
    public String EditUser(@PathVariable String userId, Model model) {
        
        model.addAttribute("user", userRepository.findById(new Long(userId)).get());
        model.addAttribute("indexRoute", this.setRoute("users", "index", ""));
        model.addAttribute("actionRoute",this.setRoute("users", "update", ""));
        return "users/addEdit";
    }
    
     @RequestMapping("/users/new")
    public String NewUser(Model model) {
        
        model.addAttribute("user", new User());
        model.addAttribute("indexRoute", this.setRoute("users", "index", ""));
        model.addAttribute("actionRoute",this.setRoute("users", "create", ""));
        return "users/addEdit";
    }
    
     @RequestMapping(value="users/create", method = RequestMethod.POST)
    public String CreateUser(User user) {
       
        this.userRepository.save(user);
        return "redirect:"+this.setRoute("users", "index", "");
    }
    
     @RequestMapping(value="users/update", method = RequestMethod.POST)
    public String UpdateUser(User user) {
       
        this.userRepository.save(user);
        return "redirect:"+this.setRoute("users", "index", "");
    }
    
    @RequestMapping(value="users/delete/{userId}", method = RequestMethod.DELETE)
    public String DeleteUser(@PathVariable String userId) {
       
        this.userRepository.deleteById(new Long(userId));
        return "redirect:"+this.setRoute("users", "index", "");
    }

}
