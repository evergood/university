package com.foxminded.university.controller;

import com.foxminded.university.domain.UserForm;
import com.foxminded.university.service.major.UserService;
import config.ConfigTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;


@WebMvcTest(controllers = UserController.class)
@ContextConfiguration(classes = ConfigTest.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userServiceMock;

    @Test
    void executeSignUpFormShouldReturnResultIfValidationPasses() throws Exception {
        String firstName = "Tom";
        String lastName = "Cruze";
        String email = "123@gmail.com";
        String password = "123adfADF34#$";
        String passwordRepeat = "123adfADF34#$";

        mockMvc.perform(post("/register").
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("password", password)
                .param("passwordRepeat", passwordRepeat)
                .sessionAttr("userForm", new UserForm()))
                .andExpect(status().isOk())
                .andExpect(view().name("signUpResult"))
                .andExpect(model().attribute("userForm", hasProperty("firstName", is(firstName))))
                .andExpect(model().attribute("userForm", hasProperty("lastName", is(lastName))))
                .andExpect(model().attribute("userForm", hasProperty("email", is(email))))
                .andExpect(model().attribute("userForm", hasProperty("password", is(password))))
                .andExpect(model().attribute("userForm", hasProperty("passwordRepeat", is(passwordRepeat))));


    }

    @Test
    void executeSignUpFormShouldReturnFormIfValidationFails() throws Exception {
        String firstName = "T";
        String lastName = "Cruze";
        String email = "123@gmail.com";
        String password = "123adfADF34#$";
        String passwordRepeat = "123adfADF34#$";

        mockMvc.perform(post("/register").
                contentType(MediaType.APPLICATION_FORM_URLENCODED)
                .param("firstName", firstName)
                .param("lastName", lastName)
                .param("email", email)
                .param("password", password)
                .param("passwordRepeat", passwordRepeat)
                .sessionAttr("userForm", new UserForm()))
                .andExpect(status().isOk())
                .andExpect(view().name("register"))
                .andExpect(model().attributeHasFieldErrors("userForm", "firstName"));

    }

}