package com.aspire.ordermanagement.service.impl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.aspire.ordermanagement.exception.OrderNotFoundException;
import com.aspire.ordermanagement.repository.OrderEntityRepository;
import com.aspire.ordermanagement.repository.entity.OrderEntity;
import com.aspire.ordermanagement.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderEntityRepository orderEntityRepository;

	public OrderServiceImpl(OrderEntityRepository orderEntityRepository) {
		this.orderEntityRepository = orderEntityRepository;
	}

	@Override
	public OrderEntity createOrder(OrderEntity orderEntity) {
		orderEntity.setStatus("INPROGRESS");
		orderEntity.setCreateAt(LocalDateTime.now().toString());
		orderEntity.setUpdateAt(LocalDateTime.now().toString());
		return orderEntityRepository.save(orderEntity);
	}

	@Override
	public OrderEntity getOrderById(Long orderId) {
		return orderEntityRepository.findById(orderId)
				.orElseThrow(() -> new OrderNotFoundException("order not found for id: " + orderId));
	}

	@Override
	public OrderEntity getOrderByOrderNumber(String orderNumber) {
		return orderEntityRepository.findByOrderNumber(orderNumber)
				.orElseThrow(() -> new OrderNotFoundException("order not found for number: " + orderNumber));
	}

	@Override
	public List<OrderEntity> getOrdersByUserId(Long userId) {
		return orderEntityRepository.findByUserId(userId);
	}

	@Override
	public List<OrderEntity> getAllOrders() {
		return orderEntityRepository.findAll();
	}

	@Override
	public OrderEntity updateOrder(Long orderId, OrderEntity orderEntity) {
		OrderEntity existing = getOrderById(orderId);
		existing.setProductId(orderEntity.getProductId());
		existing.setDeliveryAdress(orderEntity.getDeliveryAdress());
		existing.setDeliveryDate(orderEntity.getDeliveryDate());
		if (orderEntity.getStatus() != null) {
			existing.setStatus(orderEntity.getStatus());
		}
		existing.setUpdateAt(LocalDateTime.now().toString());
		return orderEntityRepository.save(existing);
	}

	@Override
	public OrderEntity updateDeliveryAddress(Long orderId, String deliveryAddress) {
		OrderEntity existing = getOrderById(orderId);
		existing.setDeliveryAdress(deliveryAddress);
		existing.setUpdateAt(LocalDateTime.now().toString());
		return orderEntityRepository.save(existing);
	}

	@Override
	public OrderEntity updateDeliveryDate(Long orderId, LocalDateTime deliveryDate) {
		OrderEntity existing = getOrderById(orderId);
		existing.setDeliveryDate(deliveryDate);
		existing.setUpdateAt(LocalDateTime.now().toString());
		return orderEntityRepository.save(existing);
	}

	@Override
	public void deleteOrder(Long orderId) {
		OrderEntity existing = getOrderById(orderId);
		orderEntityRepository.delete(existing);
	}

}
