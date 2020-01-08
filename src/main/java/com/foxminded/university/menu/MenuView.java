package com.foxminded.university.menu;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class MenuView {

    private final Scanner scanner;

    @Autowired
    public MenuView(Scanner scanner) {

        this.scanner = scanner;
    }


    public void printText(String text) {
        System.out.println(text);
    }

    public char readOption() {
        return scanner.next().charAt(0);
    }

    public String readText() {
        return scanner.next();
    }

    public Integer readDigit() {
        return scanner.nextInt();
    }

}
