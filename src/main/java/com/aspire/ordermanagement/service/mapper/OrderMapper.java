package com.aspire.ordermanagement.service.mapper;

import org.springframework.stereotype.Component;

import com.aspire.ordermanagement.exception.BadRequestException;
import com.aspire.ordermanagement.exception.CustomApplicationException;
import com.aspire.ordermanagement.repository.entity.OrderEntity;
import com.aspire.ordermanagement.service.dto.OrderRequestDto;
import com.aspire.ordermanagement.service.dto.OrderResponseDto;
import com.aspire.ordermanagement.util.DateUtil;

@Component
public class OrderMapper {

	public OrderEntity toEntity(OrderRequestDto orderRequestDto) {
		if (orderRequestDto == null) {
			throw new BadRequestException("orderRequestDto is required");
		}
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUserId(orderRequestDto.getUserId());
		orderEntity.setProductId(orderRequestDto.getProductId());
		orderEntity.setDeliveryAdress(orderRequestDto.getDeliveryAdress());
		orderEntity.setStatus(orderRequestDto.getStatus());
		if (orderRequestDto.getDeliveryDate() != null) {
			orderEntity.setDeliveryDate(DateUtil.toLocalDate(orderRequestDto.getDeliveryDate()).atStartOfDay());
		}
		return orderEntity;
	}

	public OrderResponseDto toDto(OrderEntity orderEntity) {

		if (orderEntity == null) {
			throw new CustomApplicationException("orderEntity is required");
		}

		OrderResponseDto orderResponseDto = new OrderResponseDto();
		orderResponseDto.setOrderId(orderEntity.getOrderId());
		orderResponseDto.setUserId(orderEntity.getUserId());
		orderResponseDto.setProductId(orderEntity.getProductId());
		orderResponseDto.setOrderNumber(orderEntity.getOrderNumber());
		orderResponseDto.setDeliveryAdress(orderEntity.getDeliveryAdress());
		orderResponseDto.setStatus(orderEntity.getStatus());
		orderResponseDto.setDeliveryDate(DateUtil.toString(orderEntity.getDeliveryDate()));

		return orderResponseDto;
	}

}
