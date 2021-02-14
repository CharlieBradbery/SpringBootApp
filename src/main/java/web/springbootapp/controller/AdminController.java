package web.springbootapp.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.springbootapp.controller.dto.UserDto;
import web.springbootapp.model.User;
import web.springbootapp.service.RoleService;
import web.springbootapp.service.UserService;
import java.util.stream.Collectors;


@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @GetMapping
    public String allUserShow(Model model){
        model.addAttribute("user", new UserDto());
        model.addAttribute("users", userService.listUsers());
        return "users";
    }

    @PostMapping
    public String create(@ModelAttribute("user") UserDto userDto){
        User user = fromDto(userDto);
        userService.saveUser(user);
        return "redirect:/admin";
    }

    @DeleteMapping("/{id}")
    public String removeUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/admin";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") Long id, Model model){
        model.addAttribute("user", userService.findUserById(id));

        return "edit";
    }

    @PostMapping("/{id}")
    public String update(@PathVariable("id") Long id, @ModelAttribute("user") UserDto userDto) {
        User user = fromDto(userDto);
        user.setId(id);
        userService.updateUser(user);
        return "redirect:/admin";
    }


    private User fromDto(UserDto userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());
        user.setEmail(userDto.getEmail());
        user.setRoles(userDto.getRoles() == null ? null : userDto.getRoles().stream()
                .map(roleService::findByRoleName)
                .collect(Collectors.toSet())
        );
        return user;
    }
}
