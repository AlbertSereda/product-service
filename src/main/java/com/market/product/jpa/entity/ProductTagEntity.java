package com.market.product.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_tag")
@Schema(description = "Tags for products")
public class ProductTagEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the product tag")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Identifier of the product")
    private ProductEntity product;

    @Column(name = "tag", nullable = false, length = 20)
    @Schema(description = "Tag for the product")
    private String tag;
}
