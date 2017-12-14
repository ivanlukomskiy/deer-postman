package com.ivanlukomskiy.deer.postman.model;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * Created by ivanl <ilukomskiy@sbdagroup.com> on 12.12.2017.
 */
@SpringBootApplication(scanBasePackages = {"com.ivanlukomskiy.deer.postman"})
@EnableJpaRepositories(basePackages = {"com.ivanlukomskiy.deer.postman.repository"})
public class Application {

    public static void main(String[] args) {
        new SpringApplication(Application.class).run(args);
    }
}
