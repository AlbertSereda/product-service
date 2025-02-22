package com.market.product.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Set;

@Data
@Entity
@Table(name = "product")
@Schema(description = "Product information")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the product")
    private Long id;

    @Column(name = "user_id", nullable = false)
    @Schema(description = "Identifier of the user who owns the product")
    private Long userId;

    @Column(name = "name", nullable = false, length = 100)
    @Schema(description = "Name of the product")
    private String name;

    @Column(name = "category_id", nullable = false)
    @Schema(description = "Identifier of the product category")
    private Long categoryId;

    @Column(name = "price", nullable = false, precision = 10, scale = 2)
    @Schema(description = "Price of the product")
    private BigDecimal price;

    @Column(name = "product_count", nullable = false)
    @Schema(description = "Quantity of the product (must be greater than 0)")
    private Integer productCount;

    @Column(name = "rating", precision = 2, scale = 1)
    @Schema(description = "Rating of the product")
    private BigDecimal rating;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @Schema(description = "Identifier of the product group")
    private ProductGroupEntity productGroup;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    @Schema(description = "Date of creation")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    @Schema(description = "Date of the last update")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "is_archive", nullable = false)
    @Schema(description = "Archive status of the product")
    private Boolean isArchive = false;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Schema(description = "Tags associated with the product")
    private Set<ProductTagEntity> tags;

    @OneToMany(mappedBy = "product", cascade = CascadeType.ALL)
    @Schema(description = "Tags associated with the product")
    private Set<AttributeInfoEntity> attributes;
}
