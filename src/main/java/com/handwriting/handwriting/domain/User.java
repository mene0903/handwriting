package com.handwriting.handwriting.domain;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Table(name = "tbl_user")
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Long userNumber;

    @Column(name = "user_name" , nullable = false, length = 5)
    private String name;

    @Column(name = "login_id" , nullable = false, length = 50, unique = true)
    private String loginId;

    @Column(name = "password" , nullable = false, length = 255)
    private String password;

    @Column(name = "phone_number" , nullable = true, length = 11)
    private String phoneNumber;

    @Column(name = "email" , nullable = false, length = 255, unique = true)
    private String email;

    @Column(name = "membership_date" , nullable = false)
    private LocalDate membershipDate;

    @Column(name = "birth", nullable = true)
    private LocalDate birth;

    @PrePersist
    protected void onCreate() {
        if (this.membershipDate == null) {
            this.membershipDate = LocalDate.now();
        }
    }

    public User(String name, String loginId, String password ,String phoneNumber, String email, LocalDate birth) {
        this.name = name;
        this.loginId = loginId;
        this.email = email;
        this.password = password;
        this.phoneNumber = phoneNumber;
        this.birth = birth;
    }

    public void changeUserName(String name) {
        this.name = name;
    }

    public void changePassword(String password) {
        this.password = password;
    }

    public void changePhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
