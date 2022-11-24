package com.example.demo.request;

import com.example.demo.models.Admin;
import com.example.demo.models.Student;
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserCreateRequest {

    private String username;

    private String password;

    private String authority;

    private Student student;

    private Admin admin;
}
