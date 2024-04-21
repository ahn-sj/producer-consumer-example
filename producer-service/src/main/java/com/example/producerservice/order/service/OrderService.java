package com.example.producerservice.order.service;

import com.example.producerservice.order.domain.Order;
import com.example.producerservice.rest.client.HttpClientTemplate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderService {

    private static final Map<Long, Order> orderRepository = new HashMap<>();

    static {
        orderRepository.put(1L, new Order(1L, 1L, 1L, 1000));
        orderRepository.put(2L, new Order(2L, 2L, 2L, 2000));
        orderRepository.put(3L, new Order(3L, 3L, 3L, 3000));
        orderRepository.put(4L, new Order(4L, 4L, 4L, 4000));
        orderRepository.put(5L, new Order(5L, 5L, 5L, 5000));
    }

    private final HttpClientTemplate httpClientTemplate;

    public void complete(final Long orderId) {
        final Order order = orderRepository.get(orderId); // findById
        httpClientTemplate.callPostApi(order);
    }

}
