package com.blogApp.Repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.blogApp.modelDto.UserDto;
import com.blogApp.models.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    
    
    public User findByEmail(String name);
}
