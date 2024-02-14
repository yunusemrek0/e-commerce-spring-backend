package com.enoca.repository;

import com.enoca.entity.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {
     boolean existsByUsername(String username);

     boolean existsByEmail(String email);

}
