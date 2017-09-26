
package com.andresfranco.testspring.controllers;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import com.andresfranco.testspring.repositories.UserRepository;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
@Controller
public class UserController {
    
    private UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }
    
    @RequestMapping("/users")
    public String getUsers(Model model){
          
       model.addAttribute("users", userRepository.findAll());
      
       return "users/index";
    }
    @GetMapping("/users/{userId}/show")
    public String ShowUser(@PathVariable String userId, Model model){
          
       model.addAttribute("user", userRepository.findById(Long.valueOf(userId)));
      
       return "users/show";
    }
    
    
}
