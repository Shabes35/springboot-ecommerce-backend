package com.ecommerce.entity;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table( name =  "order_items")
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id ;

    @ManyToOne
    private  Order order;

    @ManyToOne
    private Product product;

    @NotNull
    @Positive
    private Integer quantity;

    @NotNull
    @PositiveOrZero
    private Double price;


}
