package com.example.common.rest.client;

import com.example.common.domain.Order;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestClient;

import java.util.List;

public class HttpClientTemplate {

    private static final RestClient client = RestClient.create();

    public static <T> List<T> callGetApi(final String uri) {
        return client.get()
                .uri(uri)
                .retrieve()
                .body(new ParameterizedTypeReference<>(){});
    }

    public static void callPostApi(final String uri, final Long id, final String serializedOrder) {
        client.post()
                .uri(uri)
                .body(serializedOrder)
                .contentType(MediaType.APPLICATION_JSON)
                .retrieve()
                .toBodilessEntity();
    }

}
