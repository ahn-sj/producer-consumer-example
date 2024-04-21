package com.example.producerservice.order.service;

import com.example.common.rest.client.HttpClientTemplate;
import com.example.common.domain.Order;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Map<Long, Order> orderRepository = new HashMap<>();

    private final ObjectMapper objectMapper;

    static {
        orderRepository.put(1L, new Order(1L, 1L, 1L, 1000));
        orderRepository.put(2L, new Order(2L, 2L, 2L, 2000));
        orderRepository.put(3L, new Order(3L, 3L, 3L, 3000));
        orderRepository.put(4L, new Order(4L, 4L, 4L, 4000));
        orderRepository.put(5L, new Order(5L, 5L, 5L, 5000));
    }

    public void complete(final Long orderId) {
        final Order order = orderRepository.get(orderId); // findById

        HttpClientTemplate.callPostApi(
                String.format("http://localhost:8083/api/orders/" + orderId + "/complete"),
                orderId,
                convertToString(order)); // TODO: static 메서드여도 되나? (singleton)
    }

    private String convertToString(final Order order) {
        try {
            return objectMapper.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}
