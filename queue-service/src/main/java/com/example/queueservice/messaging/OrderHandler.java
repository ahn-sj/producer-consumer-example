package com.example.queueservice.messaging;

import com.example.common.domain.Order;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public ResponseEntity<Void> send(@PathVariable("orderId") Long orderId, @RequestBody String serializedReceipt) {
        orderProcessor.send(orderId, serializedReceipt);
        return ResponseEntity.noContent().build();
    }

    /**
     * Consumer listens to this endpoint
     *
     * @return
     */
    @GetMapping("/orders")
    public List<Order> receive(@RequestParam("limit") int limit) {
        return orderProcessor.receive(limit);
    }

}
