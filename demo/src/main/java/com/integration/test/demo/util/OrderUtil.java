package com.integration.test.demo.util;


import com.integration.test.demo.dto.OrderDto;
import com.integration.test.demo.entity.OrderEntity;

public class OrderUtil {

    public static OrderEntity mapOrderDataToOrderEntity(OrderDto orderData) {
        return OrderEntity.builder()
                .bid(getBidToOrderEntity(orderData))
                .orderStatus(orderData.getOrderStatus())
                .userName(orderData.getUserName())
                .email(orderData.getEmail())
                .itemCode(orderData.getItemCode())
                .itemCategory(orderData.getItemCategory())
                .price(orderData.getPrice())
                .orderedDateTime(orderData.getOrderedDateTime())
                .countryCode(orderData.getCountryCode())
                .countryName(orderData.getCountryName())
                .state(orderData.getState())
                .city(orderData.getCity())
                .createdDateTime(System.currentTimeMillis())
                .lastUpdatedDateTime(System.currentTimeMillis())
                .build();
    }

    private static String getBidToOrderEntity(OrderDto orderDto) {
        return "id_" + orderDto.getItemCode() + "_" + orderDto.getUserName() + "_" + orderDto.getOrderedDateTime();
    }
}
