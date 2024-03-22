package com.istad.demo.Repository;

import com.istad.demo.modol.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {
//    boolean existByName(String name);
}