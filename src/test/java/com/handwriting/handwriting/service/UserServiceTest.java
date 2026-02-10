package com.handwriting.handwriting.service;


import com.handwriting.handwriting.domain.User;
import com.handwriting.handwriting.dto.request.RegisterRequest;
import com.handwriting.handwriting.repository.UserRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    private RegisterRequest createUser1(String password) {
        return new RegisterRequest("user1", "user1", password, "010-000-000", "aaa@123", LocalDate.parse("2022-01-01"));
    }

    private RegisterRequest createUser2(String password) {
        return new RegisterRequest("user2", "user1", password, "010-000-000", "aaa@123", LocalDate.parse("2022-01-01"));
    }

    @Test
    @DisplayName("회원가입 성공")
    void registerSuccess() {
        //given
        RegisterRequest user1Ex = createUser1("password123");
        //when
        userService.registerUser(user1Ex);
        User user = userRepository.findByLoginId("user1")
                .orElseThrow();
        //then
        Assertions.assertThat(user.getName()).isEqualTo(user1Ex.getName());
        Assertions.assertThat(passwordEncoder.matches("password123", user.getPassword()))
                .isTrue();
    }

    @Test
    @DisplayName("회원가입 실패 (동일 아이디)")
    void registerFail() {
        //given
        RegisterRequest user1 = createUser1("password1");
        RegisterRequest user2 = createUser2("password2");
        //when
        userService.registerUser(user1);
        //then
        Assertions.assertThatThrownBy(() -> userService.registerUser(user2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("중복");
    }
}
