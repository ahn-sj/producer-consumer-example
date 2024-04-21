package com.example.common.domain;

public class Order {

    private Long orderId;
    private Long productId;
    private Long userId;
    private int price;

    public Order() {
    }

    public Order(final Long orderId, final Long productId, final Long userId, final int price) {
        this.orderId = orderId;
        this.productId = productId;
        this.userId = userId;
        this.price = price;
    }

    public Long getOrderId() {
        return orderId;
    }

    public Long getProductId() {
        return productId;
    }

    public Long getUserId() {
        return userId;
    }

    public int getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Order{" +
                "orderId=" + orderId +
                ", productId=" + productId +
                ", userId=" + userId +
                ", price=" + price +
                '}';
    }
}
