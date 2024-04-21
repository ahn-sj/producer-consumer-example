package com.example.producerservice.order.controller;

import com.example.producerservice.order.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class OrderController {

    private final OrderService OrderService;

    @PostMapping("/orders/{orderId}/complete")
    public void complete(@PathVariable("orderId") final Long orderId) {
        OrderService.complete(orderId);
    }

}
