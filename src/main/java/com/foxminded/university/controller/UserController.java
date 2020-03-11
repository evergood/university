package com.foxminded.university.controller;

import com.foxminded.university.domain.User;
import com.foxminded.university.domain.UserForm;
import com.foxminded.university.service.major.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class UserController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder;

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
                .firstName(userForm.getFirstName())
                .lastName(userForm.getLastName())
                .email(userForm.getEmail())
                .password(passwordEncoder.encode(userForm.getPassword()))
                .build();
        userService.signUp(user);
        return "signUpResult";
    }

    @RequestMapping("/")
    public String welcome() {
        return "welcome";
    }
}
