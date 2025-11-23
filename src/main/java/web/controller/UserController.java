package web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import web.model.User;
import web.repository.UserRepository;

@Controller
@RequestMapping(path = "/user")
public class UserController {
    private final UserRepository userRepository;

    public UserController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @GetMapping({"", "/"})
    public String index(Model model) {
        model.addAttribute("users", userRepository.findAll(50));
        return "index";
    }

    @GetMapping("/add")
    public String add(User user, Model model) {
        System.out.println(user);
        model.addAttribute("user", user);
        model.addAttribute("title", "Add User");
        model.addAttribute("url", "/user/add");
        return "form";
    }

    @PostMapping("/add")
    public String addUser(User user) {
        userRepository.add(user);
        return "redirect:/user";
    }

    @GetMapping("/{id}/edit")
    public String edit(@PathVariable("id") Long userId, Model model) {
        model.addAttribute("user", userRepository.findById(userId));
        model.addAttribute("title", "Update User" );
        model.addAttribute("url", "/user/edit");
        return  "form";
    }

    @PostMapping("/edit")
    public String editUser(User user) {
        userRepository.update(user);
        return "redirect:/user";
    }

    @GetMapping("/user-mock")
    public String userMock(Model model) {
        User user = new User("Igor", "Mun", "munigor.kr@gmail.com", 34);
        userRepository.add(user);
        return "redirect:/user";
    }

    @PostMapping("/{id}/delete")
    public String remove(@PathVariable("id") Long userId) {
        userRepository.delete(userId);
        return "redirect:/user";
    }
}
