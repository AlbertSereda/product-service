package com.market.product.jpa.entity;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "attribute_info")
@Schema(description = "Values for product attributes")
public class AttributeInfoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the attribute value")
    private Long id;

    @Column(name = "value", nullable = false, length = 100)
    @Schema(description = "Value of the attribute")
    private String value;

    @Column(name = "attribute_id", nullable = false)
    @Schema(description = "Identifier of the attribute")
    private Long attributeId;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false)
    @Schema(description = "Identifier of the product")
    private ProductEntity product;

    @Column(name = "creation_date", nullable = false, updatable = false)
    @CreationTimestamp
    @Schema(description = "Date of creation")
    private LocalDateTime creationDate = LocalDateTime.now();

    @Column(name = "update_date", nullable = false)
    @UpdateTimestamp
    @Schema(description = "Date of the last update")
    private LocalDateTime updateDate = LocalDateTime.now();

    @Column(name = "is_archive", nullable = false)
    @Schema(description = "Archive status of the attribute value")
    private Boolean isArchive = false;
}
