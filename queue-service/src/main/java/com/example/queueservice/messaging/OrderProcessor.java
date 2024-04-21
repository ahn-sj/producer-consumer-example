package com.example.queueservice.messaging;

import com.example.common.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Slf4j
@Component
@RequiredArgsConstructor
public class OrderProcessor {

    private final BlockingQueue<Order> orderCompleteQueue = new LinkedBlockingQueue<>(10);
    private final ObjectMapper objectMapper;

    public void send(final Long orderId, final String serializedReceipt) {
        final Order order = flatBodyToDeserializedObject(serializedReceipt, Order.class);
        orderCompleteQueue.add(order);
        log.info("[QueueName - OrderCompleteQueue][Command: push] input = {}, size = {}", serializedReceipt, orderCompleteQueue.size());
    }

    private <T> T flatBodyToDeserializedObject(final String serializedBody, final Class<T> clazz) {
        try {
            return clazz.cast(objectMapper.readValue(serializedBody, clazz));
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public List<Order> receive(final int limit) {
        final List<Order> orders = getOrdersOrDefault(limit);
        log.info("[QueueName - OrderCompleteQueue][Command: poll] limit = {}, orders = {}", limit, orders);
        return orders;
    }

    private List<Order> getOrdersOrDefault(final int limit) {
        if(orderCompleteQueue.isEmpty()) {
            return Collections.emptyList();
        }
        return IntStream.range(0, limit)
                .mapToObj(i -> orderCompleteQueue.poll())
                .takeWhile(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
