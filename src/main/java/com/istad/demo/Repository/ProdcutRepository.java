package com.istad.demo.Repository;

import com.istad.demo.modol.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdcutRepository extends JpaRepository<Product, Integer> {

}
