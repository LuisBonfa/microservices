package com.senior.challenge.user;

import com.senior.challenge.user.utils.InitializationControl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class UserApplication extends InitializationControl {
    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
