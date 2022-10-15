package com.blogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.blogApp.models.Role;

public interface RoleRespository extends JpaRepository<Role, Integer> {

}
