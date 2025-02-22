package com.market.product.jpa.entity;

import jakarta.persistence.*;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import java.math.BigDecimal;
import java.time.LocalDate;

@Data
@Entity
@Table(name = "price_history")
@Schema(description = "Price change history for products")
public class PriceHistoryEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    @Schema(description = "Unique identifier for the price history record")
    private Long id;

    @Column(name = "product_id", nullable = false)
    @Schema(description = "Identifier of the product")
    private Long productId;

    @Column(name = "old_price", nullable = false, precision = 10, scale = 2)
    @Schema(description = "Previous price of the product")
    private BigDecimal oldPrice;

    @Column(name = "change_date", nullable = false)
    @Schema(description = "Date of the price change")
    private LocalDate changeDate = LocalDate.now();
}
