package com.ecommerce.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class UserController {
    @Autowired private UserService service;

    private UserRepository repo;

    @GetMapping("/users")
    public String  showUserList(Model model){
        List<User> listUsers = service.listAll();
        model.addAttribute("ListUsers", listUsers);

        return "users";
    }

    @GetMapping("/users/new")
    public String showNewForm(Model model){
        model.addAttribute("user", new User());
        return "user_form";
    }

    @PostMapping("/users/save")
    public String saveUser(User user, RedirectAttributes ra){
            repo.save(user);
        ra.addFlashAttribute("message", "The user has been saved successfully");
        return "redirect:/users";
    }

}
