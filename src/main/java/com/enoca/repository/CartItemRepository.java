package com.enoca.repository;

import com.enoca.entity.CartItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Set;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    @Query("SELECT c FROM CartItem c WHERE c.cart.id=?1 AND c.order IS NULL")
    List<CartItem> getByCartIdAndOrderNull(Long cartId);

    List<CartItem> getByOrderId(Long orderId);

    @Query("SELECT c FROM CartItem c WHERE c.order.id IN :orderIds")
    List<CartItem> getByOrderIdSet(@Param("orderIds") Set<Long> orderIds);
}
