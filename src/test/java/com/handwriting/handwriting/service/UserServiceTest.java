package com.handwriting.handwriting.service;


import com.handwriting.handwriting.repository.User;
import com.handwriting.handwriting.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.Optional;

@SpringBootTest
@Transactional
public class UserServiceTest {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    private User createUser1(String id) {
        return new User(null, "test1", id, "123", null ,"111@email" , null, LocalDate.parse("2001-01-01"));
    }

    private User createUser2(String id) {
        return new User(null, "test1", id, "123", null ,"111@email" , null, LocalDate.parse("2001-01-01"));
    }

    @Test
    @DisplayName("회원가입 성공")
    void registerSuccess() {
        //given
        User user1 = createUser1("id1");
        //when
        userService.registerUser(user1);
        User registUser1 = userRepository.findById("id1").get();
         //then
        Assertions.assertThat(registUser1.getId()).isEqualTo(user1.getId());
    }

    @Test
    @DisplayName("회원가입 실패 (동일 아이디)")
    void registerFail() {
        //given
        User user1 = createUser1("id1");
        User user2 = createUser2("id1");

        //when
        userService.registerUser(user1);

        //then
        Assertions.assertThatThrownBy(() -> userService.registerUser(user2))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("아이디가 중복되었습니다.");
    }

}
