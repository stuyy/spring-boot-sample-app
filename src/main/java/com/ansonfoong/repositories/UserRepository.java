package com.ansonfoong.repositories;

import com.ansonfoong.models.User;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
    
}

