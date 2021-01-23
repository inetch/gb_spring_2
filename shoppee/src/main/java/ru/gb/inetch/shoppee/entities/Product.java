package ru.gb.inetch.shoppee.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "prd_product")
@Data
@NoArgsConstructor
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotNull(message = "Title not null")
    @Size(min = 6, message = "Title legth min 5 sym")
    @Column(name = "title")
    private String title;

    @NotNull(message = "price not null")
    @Min(value = 1, message = "Min 1")
    @Column(name = "price")
    private Long price;


}