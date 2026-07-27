package com.aspire.ordermanagement.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import com.aspire.ordermanagement.repository.entity.OrderEntity;
import com.aspire.ordermanagement.service.dto.OrderRequestDto;
import com.aspire.ordermanagement.service.mapper.OrderMapper;

@ExtendWith(MockitoExtension.class)
public class OrderMapperTest  {

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
		assertEquals(orderRequestDto.getDeliveryAdress(), orderEntity.getDeliveryAdress());
	}

}