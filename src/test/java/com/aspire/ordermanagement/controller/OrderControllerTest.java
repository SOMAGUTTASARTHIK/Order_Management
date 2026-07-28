package com.aspire.ordermanagement.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.ResponseEntity;

import com.aspire.ordermanagement.repository.entity.OrderEntity;
import com.aspire.ordermanagement.service.OrderService;
import com.aspire.ordermanagement.service.dto.CommonResponseDto;
import com.aspire.ordermanagement.service.dto.OrderRequestDto;
import com.aspire.ordermanagement.service.dto.OrderResponseDto;
import com.aspire.ordermanagement.service.mapper.OrderMapper;

@ExtendWith(MockitoExtension.class)
public class OrderControllerTest {

	@InjectMocks
	private OrderController orderController;

	@Mock
	private OrderService orderService;

	@Mock
	private OrderMapper orderMapper;

	@Test
	void createOrderTest() {
		OrderRequestDto requestDto = new OrderRequestDto();
		OrderEntity entity = new OrderEntity();
		OrderResponseDto responseDto = new OrderResponseDto();

		Mockito.when(orderMapper.toEntity(requestDto)).thenReturn(entity);
		Mockito.when(orderService.createOrder(entity)).thenReturn(entity);
		Mockito.when(orderMapper.toDto(entity)).thenReturn(responseDto);

		ResponseEntity<CommonResponseDto<OrderResponseDto>> response = orderController.createOrder(requestDto);

		assertNotNull(response);
		assertEquals(201, response.getStatusCode().value());
		assertEquals(responseDto, response.getBody().getData());
	}

	@Test
	void getOrderByIdTest() {
		OrderEntity entity = new OrderEntity();
		OrderResponseDto responseDto = new OrderResponseDto();

		Mockito.when(orderService.getOrderById(1L)).thenReturn(entity);
		Mockito.when(orderMapper.toDto(entity)).thenReturn(responseDto);

		ResponseEntity<CommonResponseDto<OrderResponseDto>> response = orderController.getOrderById(1L);

		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(responseDto, response.getBody().getData());
	}

	@Test
	void getOrderByOrderNumberTest() {
		OrderEntity entity = new OrderEntity();
		OrderResponseDto responseDto = new OrderResponseDto();

		Mockito.when(orderService.getOrderByOrderNumber("ORD-123")).thenReturn(entity);
		Mockito.when(orderMapper.toDto(entity)).thenReturn(responseDto);

		ResponseEntity<CommonResponseDto<OrderResponseDto>> response = orderController.getOrderByOrderNumber("ORD-123");

		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(responseDto, response.getBody().getData());
	}

	@Test
	void getOrdersByUserIdTest() {
		Mockito.when(orderService.getOrdersByUserId(5L)).thenReturn(List.of(new OrderEntity()));
		Mockito.when(orderMapper.toDto(any(OrderEntity.class))).thenReturn(new OrderResponseDto());

		ResponseEntity<CommonResponseDto<List<OrderResponseDto>>> response = orderController.getOrdersByUserId(5L);

		assertNotNull(response);
		assertEquals(1, response.getBody().getData().size());
	}

	@Test
	void getAllOrdersTest() {
		Mockito.when(orderService.getAllOrders()).thenReturn(List.of(new OrderEntity(), new OrderEntity()));
		Mockito.when(orderMapper.toDto(any(OrderEntity.class))).thenReturn(new OrderResponseDto());

		ResponseEntity<CommonResponseDto<List<OrderResponseDto>>> response = orderController.getAllOrders();

		assertNotNull(response);
		assertEquals(2, response.getBody().getData().size());
	}

	@Test
	void updateOrderTest() {
		OrderRequestDto requestDto = new OrderRequestDto();
		OrderEntity entity = new OrderEntity();
		OrderResponseDto responseDto = new OrderResponseDto();

		Mockito.when(orderMapper.toEntity(requestDto)).thenReturn(entity);
		Mockito.when(orderService.updateOrder(1L, entity)).thenReturn(entity);
		Mockito.when(orderMapper.toDto(entity)).thenReturn(responseDto);

		ResponseEntity<CommonResponseDto<OrderResponseDto>> response = orderController.updateOrder(1L, requestDto);

		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
		assertEquals(responseDto, response.getBody().getData());
	}

	@Test
	void updateDeliveryAddressTest() {
		OrderEntity entity = new OrderEntity();
		OrderResponseDto responseDto = new OrderResponseDto();

		Mockito.when(orderService.updateDeliveryAddress(1L, "new address")).thenReturn(entity);
		Mockito.when(orderMapper.toDto(entity)).thenReturn(responseDto);

		ResponseEntity<CommonResponseDto<OrderResponseDto>> response =
				orderController.updateDeliveryAddress(1L, "new address");

		assertNotNull(response);
		assertEquals(responseDto, response.getBody().getData());
	}

	@Test
	void updateDeliveryDateTest() {
		OrderEntity entity = new OrderEntity();
		OrderResponseDto responseDto = new OrderResponseDto();

		Mockito.when(orderService.updateDeliveryDate(eq(1L), any())).thenReturn(entity);
		Mockito.when(orderMapper.toDto(entity)).thenReturn(responseDto);

		ResponseEntity<CommonResponseDto<OrderResponseDto>> response =
				orderController.updateDeliveryDate(1L, "20/02/2026");

		assertNotNull(response);
		assertEquals(responseDto, response.getBody().getData());
	}

	@Test
	void deleteOrderTest() {
		ResponseEntity<CommonResponseDto<Void>> response = orderController.deleteOrder(1L);

		Mockito.verify(orderService).deleteOrder(1L);
		assertNotNull(response);
		assertEquals(200, response.getStatusCode().value());
	}

}