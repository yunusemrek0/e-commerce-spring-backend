package com.enoca.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import lombok.experimental.SuperBuilder;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@SuperBuilder

@Entity
@Table(name = "t_cart_item")
public class CartItem extends BaseEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer quantity;

    @OneToOne
    @JoinColumn(name = "product_id")
    private Product product;


    @ManyToOne
    private Cart cart;

    @ManyToOne
    @JsonIgnore
    private Order order;

    private Double productPrice;



}
