package com.example.queueservice.messaging;

import com.example.queueservice.domain.Order;
import com.example.queueservice.domain.OrderReceipt;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProcessor {

    private BlockingQueue<OrderReceipt> orderCompleteQueue = new LinkedBlockingQueue();

    private final ObjectMapper objectMapper;

    public void send(final Long orderId, final Order order) {
        final String receipt = convertToOrder(order);
        final OrderReceipt orderReceipt = new OrderReceipt(orderId, receipt);

        orderCompleteQueue.add(orderReceipt);

        log.info("[QueueName - OrderCompleteQueue][Command: push] size = {}", orderCompleteQueue.size());
    }

    private String convertToOrder(final Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
