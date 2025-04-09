package com.QuickBite.QuickBite.repository;

import com.QuickBite.QuickBite.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String Username);
}
