package com.aspire.ordermanagement.service;

import java.time.LocalDateTime;
import java.util.List;

import com.aspire.ordermanagement.repository.entity.OrderEntity;

public interface OrderService {

	OrderEntity createOrder(OrderEntity orderEntity);

	OrderEntity getOrderById(Long orderId);

	OrderEntity getOrderByOrderNumber(String orderNumber);

	List<OrderEntity> getOrdersByUserId(Long userId);

	List<OrderEntity> getAllOrders();

	OrderEntity updateOrder(Long orderId, OrderEntity orderEntity);

	OrderEntity updateDeliveryAddress(Long orderId, String deliveryAddress);

	OrderEntity updateDeliveryDate(Long orderId, LocalDateTime deliveryDate);

	void deleteOrder(Long orderId);

}
