package com.blogApp.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestMapping;

import com.blogApp.models.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer>{

}
