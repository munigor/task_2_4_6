package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.repository.UserRepository;
import web.service.UserService;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("users", userService.findAll(50));
        return "index";
    }

    @GetMapping("/add")
    public String add(User user, Model model) {
        model.addAttribute("user", user);
        model.addAttribute("title", "Add User");
        model.addAttribute("url", "/user/add");
        return "form";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userService.add(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userService.findById(userId));
        model.addAttribute("title", "Update User" );
        model.addAttribute("url", "/user/edit");
        return  "form";
    }

    @PostMapping("/edit")
    public String editUser(User user) {
        userService.update(user);
        return "redirect:/user";
    }

    @PostMapping("/{id}/delete")
    public String remove(@PathVariable("id") Long userId) {
        userService.delete(userId);
        return "redirect:/user";
    }
}
