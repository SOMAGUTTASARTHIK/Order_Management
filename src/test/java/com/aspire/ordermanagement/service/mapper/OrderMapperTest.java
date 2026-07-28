package com.aspire.ordermanagement.service.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.ordermanagement.exception.BadRequestException;
import com.aspire.ordermanagement.exception.CustomApplicationException;
import com.aspire.ordermanagement.repository.entity.OrderEntity;
import com.aspire.ordermanagement.service.dto.OrderRequestDto;
import com.aspire.ordermanagement.service.dto.OrderResponseDto;
import com.aspire.ordermanagement.service.mapper.OrderMapper;
import com.aspire.ordermanagement.util.DateUtil;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTest {

	@InjectMocks
	private OrderMapper orderMapper;

	@Test
	void toEntityTest() {
		OrderRequestDto orderRequestDto = new OrderRequestDto();
		orderRequestDto.setDeliveryAdress("seeghalli, banglore");
		orderRequestDto.setDeliveryDate("22/12/2028");
		orderRequestDto.setOrderNumber("1");
		orderRequestDto.setStatus("pending");
		orderRequestDto.setProductId(234L);

		OrderEntity orderEntity = orderMapper.toEntity(orderRequestDto);
		assertNotNull(orderEntity);
		assertEquals(orderEntity.getDeliveryAdress(), orderRequestDto.getDeliveryAdress());
	}

	@Test
	void toEntityTest_BadRequestException() {
		BadRequestException exception = assertThrows(BadRequestException.class, () -> orderMapper.toEntity(null));
		assertEquals("orderRequestDto is required", exception.getMessage());
	}

	@Test
	void toDtoTest() {
		OrderEntity orderEntity = new OrderEntity();
		orderEntity.setUserId(1l);
		orderEntity.setProductId(1l);
		orderEntity.setDeliveryAdress("seeghalli");
		orderEntity.setDeliveryDate(DateUtil.toLocalDate("20/02/2026").atStartOfDay());

		OrderResponseDto orderResponseDto = orderMapper.toDto(orderEntity);
		assertNotNull(orderResponseDto);
		assertEquals(orderEntity.getOrderId(), orderResponseDto.getOrderId());
	}

	@Test
	void toDtoTest_CustomApplicationException() {
		CustomApplicationException exception = assertThrows(CustomApplicationException.class,
				() -> orderMapper.toDto(null));
		assertEquals("orderEntity is required", exception.getMessage());
	}

}