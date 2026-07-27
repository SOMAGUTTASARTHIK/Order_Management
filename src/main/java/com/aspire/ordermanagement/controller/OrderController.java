package com.aspire.ordermanagement.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.aspire.ordermanagement.repository.entity.OrderEntity;
import com.aspire.ordermanagement.service.OrderService;
import com.aspire.ordermanagement.service.dto.CommonResponseDto;
import com.aspire.ordermanagement.service.dto.OrderRequestDto;
import com.aspire.ordermanagement.service.dto.OrderResponseDto;
import com.aspire.ordermanagement.service.mapper.OrderMapper;
import com.aspire.ordermanagement.util.DateUtil;

@RestController
@RequestMapping("/api/orders")
public class OrderController {

	private final OrderService orderService;
	private final OrderMapper orderMapper;

	public OrderController(OrderService orderService, OrderMapper orderMapper) {
		this.orderService = orderService;
		this.orderMapper = orderMapper;
	}

	@PostMapping
	public ResponseEntity<CommonResponseDto<OrderResponseDto>> createOrder(
			@RequestBody OrderRequestDto orderRequestDto) {
		OrderEntity entity = orderMapper.toEntity(orderRequestDto);
		OrderEntity saved = orderService.createOrder(entity);
		return ResponseEntity.status(HttpStatus.CREATED)
				.body(buildResponse(HttpStatus.CREATED.value(), orderMapper.toDto(saved), "order created successfully"));
	}

	@GetMapping("/{orderId}")
	public ResponseEntity<CommonResponseDto<OrderResponseDto>> getOrderById(@PathVariable Long orderId) {
		OrderEntity entity = orderService.getOrderById(orderId);
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), orderMapper.toDto(entity), "order fetched successfully"));
	}

	@GetMapping("/number/{orderNumber}")
	public ResponseEntity<CommonResponseDto<OrderResponseDto>> getOrderByOrderNumber(
			@PathVariable String orderNumber) {
		OrderEntity entity = orderService.getOrderByOrderNumber(orderNumber);
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), orderMapper.toDto(entity), "order fetched successfully"));
	}

	@GetMapping("/user/{userId}")
	public ResponseEntity<CommonResponseDto<List<OrderResponseDto>>> getOrdersByUserId(@PathVariable Long userId) {
		List<OrderResponseDto> orders = orderService.getOrdersByUserId(userId).stream()
				.map(orderMapper::toDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), orders, "orders fetched successfully"));
	}

	@GetMapping
	public ResponseEntity<CommonResponseDto<List<OrderResponseDto>>> getAllOrders() {
		List<OrderResponseDto> orders = orderService.getAllOrders().stream()
				.map(orderMapper::toDto)
				.collect(Collectors.toList());
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), orders, "orders fetched successfully"));
	}

	@PutMapping("/{orderId}")
	public ResponseEntity<CommonResponseDto<OrderResponseDto>> updateOrder(@PathVariable Long orderId,
			@RequestBody OrderRequestDto orderRequestDto) {
		OrderEntity entity = orderMapper.toEntity(orderRequestDto);
		OrderEntity updated = orderService.updateOrder(orderId, entity);
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), orderMapper.toDto(updated), "order updated successfully"));
	}

	@PatchMapping("/{orderId}/deliveryAddress")
	public ResponseEntity<CommonResponseDto<OrderResponseDto>> updateDeliveryAddress(@PathVariable Long orderId,
			@RequestParam String deliveryAddress) {
		OrderEntity updated = orderService.updateDeliveryAddress(orderId, deliveryAddress);
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), orderMapper.toDto(updated), "delivery address updated successfully"));
	}

	@PatchMapping("/{orderId}/deliveryDate")
	public ResponseEntity<CommonResponseDto<OrderResponseDto>> updateDeliveryDate(@PathVariable Long orderId,
			@RequestParam String deliveryDate) {
		LocalDateTime parsedDate = DateUtil.toLocalDate(deliveryDate).atStartOfDay();
		OrderEntity updated = orderService.updateDeliveryDate(orderId, parsedDate);
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), orderMapper.toDto(updated), "delivery date updated successfully"));
	}

	@DeleteMapping("/{orderId}")
	public ResponseEntity<CommonResponseDto<Void>> deleteOrder(@PathVariable Long orderId) {
		orderService.deleteOrder(orderId);
		return ResponseEntity.ok(buildResponse(HttpStatus.OK.value(), null, "order deleted successfully"));
	}

	private <T> CommonResponseDto<T> buildResponse(int status, T data, String message) {
		CommonResponseDto<T> response = new CommonResponseDto<>();
		response.setStatus(status);
		response.setData(data);
		response.setMessage(message);
		response.setTimeStamp(LocalDateTime.now());
		return response;
	}

}
