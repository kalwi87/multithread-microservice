package com.biel.microservice.repository;

import com.biel.microservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

@Transactional(readOnly = true)
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String name);

    User findUserById(Long id);

}
