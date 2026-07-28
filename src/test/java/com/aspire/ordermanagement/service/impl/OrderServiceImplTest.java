package com.aspire.ordermanagement.service.impl;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.ordermanagement.exception.OrderNotFoundException;
import com.aspire.ordermanagement.repository.OrderEntityRepository;
import com.aspire.ordermanagement.repository.entity.OrderEntity;

@ExtendWith(MockitoExtension.class)
public class OrderServiceImplTest {

	@InjectMocks
	private OrderServiceImpl orderServiceImpl;

	@Mock
	private OrderEntityRepository orderEntityRepository;

	@Test
	void createOrderTest() {
		OrderEntity input = new OrderEntity();
		input.setProductId(1L);

		Mockito.when(orderEntityRepository.save(any(OrderEntity.class))).thenReturn(input);

		OrderEntity result = orderServiceImpl.createOrder(input);

		assertNotNull(result);
		assertEquals("INPROGRESS", result.getStatus());
	}

	@Test
	void getOrderByIdTest() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderId(11L);

		Mockito.when(orderEntityRepository.findById(11L)).thenReturn(Optional.of(orderEntity));

		OrderEntity result = orderServiceImpl.getOrderById(11L);

		assertNotNull(result);
		assertEquals(11L, result.getOrderId());
	}

	@Test
	void getOrderByIdTest_orderNotFoundException() {
		Mockito.when(orderEntityRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.getOrderById(99L));
	}

	@Test
	void getOrderByOrderNumberTest() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setOrderNumber("ORD-123");

		Mockito.when(orderEntityRepository.findByOrderNumber("ORD-123")).thenReturn(Optional.of(orderEntity));

		OrderEntity result = orderServiceImpl.getOrderByOrderNumber("ORD-123");

		assertNotNull(result);
		assertEquals("ORD-123", result.getOrderNumber());
	}

	@Test
	void getOrderByOrderNumberTest_orderNotFoundException() {
		Mockito.when(orderEntityRepository.findByOrderNumber("UNKNOWN")).thenReturn(Optional.empty());

		assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.getOrderByOrderNumber("UNKNOWN"));
	}

	@Test
	void getOrdersByUserIdTest() {
		Mockito.when(orderEntityRepository.findByUserId(5L)).thenReturn(List.of(new OrderEntity()));

		List<OrderEntity> result = orderServiceImpl.getOrdersByUserId(5L);

		assertEquals(1, result.size());
	}

	@Test
	void getAllOrdersTest() {
		Mockito.when(orderEntityRepository.findAll()).thenReturn(List.of(new OrderEntity(), new OrderEntity()));

		List<OrderEntity> result = orderServiceImpl.getAllOrders();

		assertEquals(2, result.size());
	}

	@Test
	void updateOrderTest() {
		OrderEntity existing = new OrderEntity();
		existing.setOrderId(1L);

		OrderEntity updates = new OrderEntity();
		updates.setDeliveryAdress("seeghalli");
		updates.setStatus("CONFIRMED");

		Mockito.when(orderEntityRepository.findById(1L)).thenReturn(Optional.of(existing));
		Mockito.when(orderEntityRepository.save(any(OrderEntity.class))).thenReturn(existing);

		OrderEntity result = orderServiceImpl.updateOrder(1L, updates);

		assertNotNull(result);
		assertEquals("seeghalli", result.getDeliveryAdress());
		assertEquals("CONFIRMED", result.getStatus());
	}

	@Test
	void updateOrderTest_orderNotFoundException() {
		Mockito.when(orderEntityRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.updateOrder(99L, new OrderEntity()));
	}

	@Test
	void updateDeliveryAddressTest() {
		OrderEntity existing = new OrderEntity();
		existing.setOrderId(1L);

		Mockito.when(orderEntityRepository.findById(1L)).thenReturn(Optional.of(existing));
		Mockito.when(orderEntityRepository.save(any(OrderEntity.class))).thenReturn(existing);

		OrderEntity result = orderServiceImpl.updateDeliveryAddress(1L, "new address");

		assertEquals("new address", result.getDeliveryAdress());
	}

	@Test
	void updateDeliveryAddressTest_orderNotFoundException() {
		Mockito.when(orderEntityRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.updateDeliveryAddress(99L, "address"));
	}

	@Test
	void updateDeliveryDateTest() {
		OrderEntity existing = new OrderEntity();
		existing.setOrderId(1L);

		LocalDateTime newDate = LocalDateTime.of(2028, 12, 22, 0, 0);

		Mockito.when(orderEntityRepository.findById(1L)).thenReturn(Optional.of(existing));
		Mockito.when(orderEntityRepository.save(any(OrderEntity.class))).thenReturn(existing);

		OrderEntity result = orderServiceImpl.updateDeliveryDate(1L, newDate);

		assertEquals(newDate, result.getDeliveryDate());
	}

	@Test
	void updateDeliveryDateTest_orderNotFoundException() {
		Mockito.when(orderEntityRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(OrderNotFoundException.class,
				() -> orderServiceImpl.updateDeliveryDate(99L, LocalDateTime.now()));
	}

	@Test
	void deleteOrderTest() {
		OrderEntity existing = new OrderEntity();
		existing.setOrderId(1L);

		Mockito.when(orderEntityRepository.findById(1L)).thenReturn(Optional.of(existing));

		orderServiceImpl.deleteOrder(1L);

		Mockito.verify(orderEntityRepository).delete(existing);
	}

	@Test
	void deleteOrderTest_orderNotFoundException() {
		Mockito.when(orderEntityRepository.findById(99L)).thenReturn(Optional.empty());

		assertThrows(OrderNotFoundException.class, () -> orderServiceImpl.deleteOrder(99L));
	}

}