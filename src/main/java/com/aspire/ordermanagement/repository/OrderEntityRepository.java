package com.aspire.ordermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.aspire.ordermanagement.repository.entity.OrderEntity;

@Repository
public interface OrderEntityRepository extends JpaRepository<OrderEntity, Long>{

	Optional<OrderEntity> findByOrderNumber(String orderNumber);

	List<OrderEntity> findByUserId(Long userId);

}
