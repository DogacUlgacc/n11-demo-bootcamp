package com.dogac.order_service.application.feignDto;

import java.util.List;
import java.util.UUID;

public record CartDto(
        UUID id,
        UUID userId,
        String currency,
        List<CartItemDto> items) {

}
