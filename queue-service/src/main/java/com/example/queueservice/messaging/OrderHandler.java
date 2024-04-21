package com.example.queueservice.messaging;

import com.example.queueservice.domain.Order;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    private final ObjectMapper objectMapper;

    /**
     * Producer sends a message to this endpoint
     *
     * @return
     */
    @PostMapping("/orders/{orderId}/complete")
    public ResponseEntity<Void> send(@PathVariable Long orderId, @RequestBody Order order) {
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
