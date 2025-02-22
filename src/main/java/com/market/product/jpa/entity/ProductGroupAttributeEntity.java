package com.market.product.jpa.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table(name = "product_group_attribute",
       uniqueConstraints = @UniqueConstraint(columnNames = {"group_id", "attribute_id"}))
@Schema(description = "Groups of attributes")
public class ProductGroupAttributeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the product group attribute link")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "group_id", nullable = false)
    @Schema(description = "Identifier of the product group")
    private ProductGroupEntity productGroup;

    @Column(name = "attribute_id", nullable = false)
    @Schema(description = "Identifier of the attribute")
    private Long attributeId;
}
