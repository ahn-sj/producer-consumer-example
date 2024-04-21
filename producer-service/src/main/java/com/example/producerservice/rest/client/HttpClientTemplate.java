package com.example.producerservice.rest.client;

import com.example.producerservice.order.domain.Order;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

@Component
public class HttpClientTemplate {

    private static final RestClient client = RestClient.create();

    public void callPostApi(final Order order) {
        final ResponseEntity<Void> response = client.post()
                .uri("http://localhost:8083/api/orders/" + order.getOrderId() + "/complete")
                .body(order)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }
}
