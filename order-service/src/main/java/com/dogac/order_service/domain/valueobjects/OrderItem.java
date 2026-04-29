package com.dogac.order_service.domain.valueobjects;

import java.math.BigDecimal;

public record OrderItem(ProductId productId, int quantity, BigDecimal unitPrice, BigDecimal totalPrice) {
    public OrderItem {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be greater than zero");
        }
        if (unitPrice == null || unitPrice.signum() < 0) {
            throw new IllegalArgumentException("Unit price must be non-negative");
        }
        totalPrice = unitPrice.multiply(BigDecimal.valueOf(quantity));
    }
}
