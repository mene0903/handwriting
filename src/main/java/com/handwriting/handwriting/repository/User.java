package com.handwriting.handwriting.repository;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id //pk
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_number")
    private Long userNumber;

    @Column(name = "user_name" , nullable = false, length = 5)
    private String name;

    @Column(name = "id" , nullable = false, length = 50)
    private String id;

    @Column(name = "password" , nullable = false, length = 255)
    private String password;

    @Column(name = "phone_number" , nullable = true, length = 11)
    private String phone_number;

    @Column(name = "email" , nullable = false, length = 255)
    private String email;

    @Column(name = "membership_date" , nullable = false)
    private LocalDate membership_date;

    @Column(name = "birth", nullable = true)
    private LocalDate birth;

    @PrePersist
    protected void onCreate() {
        if (this.membership_date == null) {
            this.membership_date = LocalDate.now();
        }
    }

}
