package web.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import web.springbootapp.model.User;
import web.springbootapp.service.UserService;

@Controller
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/user")
    public String Userdata(Authentication authentication, Model model){
        User user = (User) authentication.getPrincipal();
        model.addAttribute("user", user);
        return "userdata";
    }

    @GetMapping("/user/{id}")
    public String UserdataAdmin(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));
        return "userdata";
    }
}
