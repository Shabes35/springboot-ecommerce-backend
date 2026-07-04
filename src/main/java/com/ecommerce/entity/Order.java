package com.ecommerce.entity;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name ="orders")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor


public class Order {

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private User user;

    @NotNull
    @PositiveOrZero
    private Double totalAmount;


    private String status;



}
