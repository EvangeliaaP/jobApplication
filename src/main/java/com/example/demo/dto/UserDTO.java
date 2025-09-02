package com.example.demo.dto;


import com.example.demo.enumeration.RoleEnum;
import lombok.Data;

@Data
public class UserDTO {
    private Long id;

    private String username;

    private String password;

    private String email;

    private RoleEnum role;

    public UserDTO(String username, String password, String email, RoleEnum role) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = role;
    }
}
