package com.enoca.repository;

import com.enoca.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> getByCode(String code);

    @Query("SELECT o FROM Order o WHERE o.customer.id=?1")
    List<Order> getByCustomerId(Long customerId);
}
