package com.inpad.spring.inpadspringboot.repositories;

import com.inpad.spring.inpadspringboot.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByLogin(String login);

}
