package com.foxminded.university.controller;

import com.foxminded.university.domain.User;
import com.foxminded.university.domain.UserForm;
import com.foxminded.university.service.major.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/register")
    public String executeSignUpForm(Model model) {
        model.addAttribute("userForm", new UserForm());
        return "register";
    }

    @PostMapping("/register")
    public String submitSignUpForm(@ModelAttribute("userForm") @Valid UserForm userForm,
                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "register";
        }
        User user = User.builder()
                .withFirstName(userForm.getFirstName())
                .withLastName(userForm.getLastName())
                .withEmail(userForm.getEmail())
                .withPassword(userForm.getPassword())
                .build();
        userService.signUp(user);
        return "signUpResult";
    }

    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }
}
