package com.example.queueservice.domain;

public class OrderReceipt {

    private final Long orderId;
    private final String receipt;

    public OrderReceipt(final Long orderId, final String receipt) {

        this.orderId = orderId;
        this.receipt = receipt;
    }
}
