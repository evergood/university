package com.foxminded.university.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
@Slf4j
public class ErrorsHandler {

    @ExceptionHandler(Exception.class)
    public String handleError(Exception exception) {
      LOGGER.error(String.format("Failed to load the page [%s]", exception.getMessage()));
      return "error";
    }
}
