package com.example.practice15.repositories;

import com.example.practice15.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    Optional<User> findByEmail(String email);

    @Override
    <S extends User> S saveAndFlush(S s);
}
