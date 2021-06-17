package com.senior.challenge.user.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDTO {

    private String name;
    private String password;
    private String alias;
    private String email;
    private String phone;
    private String document;
    private List<String> roles;
}
