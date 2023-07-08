package com.hms.app.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hms.app.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
    User findByUsernameAndPassword(String username,String pass);
}
