package com.example.queueservice.messaging;

import com.example.producerservice.order.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class OrderHandler {

    private final OrderProcessor orderProcessor;

    /**
     * Producer sends a message to this endpoint
     */
    @PostMapping("/orders/{orderId}/complete")
    public ResponseEntity<Void> send(@PathVariable("orderId") Long orderId, @RequestBody Order order) {
        orderProcessor.send(orderId, order);
        return ResponseEntity.noContent().build();
    }

    /**
     * Consumer listens to this endpoint
     */
    @GetMapping("/orders")
    public void listen(String message) {

    }

}
