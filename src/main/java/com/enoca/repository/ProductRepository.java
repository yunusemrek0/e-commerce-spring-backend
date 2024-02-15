package com.enoca.repository;

import com.enoca.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    boolean existsByName(String name);

    Optional<Product> findByName(String name);

    List<Product> getByNameContaining(String name);
}
