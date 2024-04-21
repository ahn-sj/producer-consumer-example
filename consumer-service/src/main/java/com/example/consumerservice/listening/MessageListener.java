package com.example.consumerservice.listening;

import com.example.common.domain.Order;
import com.example.common.rest.client.HttpClientTemplate;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MessageListener {

    private final AtomicBoolean running = new AtomicBoolean(false);

    private final ObjectMapper objectMapper;

    @PostConstruct
    public void start() {
        running.compareAndSet(false, true);
    }

    @Scheduled(fixedRate = 5000)
    public void listen() {
        if(running.get() == false) {
            return;
        }

        final List<Order> orders = objectMapper.convertValue(HttpClientTemplate.callGetApi("http://localhost:8083/api/orders?limit=3"), new TypeReference<>() {});
        // do something to process the orders
        log.info("consume time = {}, consume orders = {}", LocalDateTime.now(), orders);
    }

    @PostMapping("/change-status")
    public void changeStatus() {
        running.compareAndSet(running.get(), !running.get());
    }
}
