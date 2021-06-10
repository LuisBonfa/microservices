package com.senior.challenge.user.service;

import com.senior.challenge.user.entity.Role;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
@Scope(value = "prototype")
public class InitializationControl implements InitializingBean {

    @Autowired
    public InitializationControl() {
        System.out.println("Constructor");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
    }

    @PostConstruct
    public void postConstruct() {
        System.out.println("PostConstruct");
    }

    public void init() {
        System.out.println("init-method");
    }
}