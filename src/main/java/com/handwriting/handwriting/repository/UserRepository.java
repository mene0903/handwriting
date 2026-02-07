package com.handwriting.handwriting.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

//JPARepository 사용해서 DB 접근
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findById(String id);

    boolean existsById(String id);
}
