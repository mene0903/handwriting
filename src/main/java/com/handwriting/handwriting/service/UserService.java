package com.handwriting.handwriting.service;

import com.handwriting.handwriting.domain.User;
import com.handwriting.handwriting.repository.UserRepository;
import com.handwriting.handwriting.dto.request.RegisterRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    //회원 등록, 비밀번호 암호화 후 user 저장
    @Transactional
    public void registerUser(RegisterRequest registerRequest) {
        if(userRepository.existsByLoginId(registerRequest.getLoginId())) {
            throw new IllegalArgumentException("아이디가 중복되었습니다.");
        }

        String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

        User user = new User(
                registerRequest.getName(),
                registerRequest.getLoginId(),
                encodedPassword,
                registerRequest.getPhoneNumber(),
                registerRequest.getEmail(),
                registerRequest.getBirth()
        );

        userRepository.save(user);
    }
/*
    @Transactional(readOnly = true)
    public User login(String loginId, String password) {

        User user = userRepository.findByLoginId(loginId)
                .orElseThrow(() -> new IllegalArgumentException("회원 정보가 없습니다"));

        if(!user.getPassword().equals(password)) {
            throw new IllegalArgumentException("비밀번호가 틀렸습니다.");
        }

        return user;
    }
*/
}