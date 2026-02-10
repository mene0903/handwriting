package com.handwriting.handwriting.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDate;

@Getter
@AllArgsConstructor
public class RegisterRequest {

    private String name;
    private String loginId;
    private String password;
    private String phoneNumber;
    private String email;
    private LocalDate birth;

}
