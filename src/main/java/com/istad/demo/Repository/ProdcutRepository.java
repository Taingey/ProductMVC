package com.istad.demo.Repository;

import com.istad.demo.modol.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProdcutRepository extends JpaRepository<Product, Integer> {
    Optional<Product> findByUuid(String uuid);
    boolean existsByName(String name);
    boolean existsByUuid(String uuid);
    void deleteByUuid(String uuid);

}
