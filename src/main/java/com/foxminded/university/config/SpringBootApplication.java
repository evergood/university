package com.foxminded.university.config;

import com.foxminded.university.menu.MenuController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;
import java.util.Scanner;

@Configuration
@ComponentScan("com.foxminded.university")
@PropertySource("classpath:database.properties")
public class SpringBootApplication implements CommandLineRunner {

    private static final String URL = "url";
    private static final String USER = "dbuser";
    private static final String DRIVER = "driver";
    private static final String PASSWORD = "dbpassword";

    @Autowired
    private Environment environment;

    @Autowired
    private MenuController menuController;

    @Bean
    DataSource dataSource() {
        DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
        driverManagerDataSource.setUrl(environment.getProperty(URL));
        driverManagerDataSource.setUsername(environment.getProperty(USER));
        driverManagerDataSource.setPassword(environment.getProperty(PASSWORD));
        driverManagerDataSource.setDriverClassName(environment.getProperty(DRIVER));
        return driverManagerDataSource;
    }

    @Bean
    Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static void main(String[] args) {
        SpringApplication application = new SpringApplication(SpringBootApplication.class);
        application.run(args);
    }

    @Override
    public void run(String... args) {
        menuController.executeLoginMenu();
    }
}
