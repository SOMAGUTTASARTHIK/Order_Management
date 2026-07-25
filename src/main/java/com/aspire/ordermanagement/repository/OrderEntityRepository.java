package com.aspire.ordermanagement.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.aspire.ordermanagement.repository.entity.OrderEntity;

public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long>{

}
