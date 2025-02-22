package com.market.product.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_group")
@Schema(description = "Product group information")
public class ProductGroupEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the product group")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    @Schema(description = "Name of the product group")
    private String name;
}
