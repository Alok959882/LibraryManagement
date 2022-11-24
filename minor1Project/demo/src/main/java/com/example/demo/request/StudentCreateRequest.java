package com.example.demo.request;

import com.example.demo.models.*;
import lombok.*;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class StudentCreateRequest {

    @NotBlank
    private String name;

    @NotBlank
    private String contact;

    private String address;
    private String email;

    @NotBlank
    private String username;

    @NotBlank
    private String password;

    public Student to() {
        return Student.builder()
                .address(address)
                .contact(contact)
                .email(email)
                .name(name)
                .accountStatus(AccountStatus.ACTIVE)
                .build();
    }

    public UserCreateRequest toUser() {

        return UserCreateRequest.builder()
                .student(to())
                .username(username)
                .password(password)
                .build();
    }
}
