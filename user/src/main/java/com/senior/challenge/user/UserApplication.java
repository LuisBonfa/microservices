package com.senior.challenge.user;

import com.senior.challenge.user.service.InitializationControl;
import com.senior.challenge.user.service.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@EnableEurekaClient
@SpringBootApplication
public class UserApplication extends InitializationControl {

    public static void main(String[] args) {
        SpringApplication.run(UserApplication.class, args);
    }
}
