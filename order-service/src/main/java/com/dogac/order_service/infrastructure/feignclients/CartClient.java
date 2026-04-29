package com.dogac.order_service.infrastructure.feignclients;

import java.util.UUID;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.dogac.order_service.application.feignDto.CartDto;

@FeignClient(name = "cart-service", url = "http://localhost:8888/api/v1/carts")
public interface CartClient {
    @GetMapping("/{id}")
    CartDto getCartById(@PathVariable("id") UUID id);
}
