package com.handwriting.handwriting.repository;

import com.handwriting.handwriting.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JPARepository 사용해서 DB 접근
public interface UserRepository extends JpaRepository<User, Long> {

    boolean existsByLoginId(String loginId);
    boolean existsByEmail(String email);

    Optional<User> findByLoginId(String loginId);
}
