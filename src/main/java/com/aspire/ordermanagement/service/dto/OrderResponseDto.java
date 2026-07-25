package com.aspire.ordermanagement.service.dto;

public class OrderResponseDto {

	private Long orderId;
	private Long userId;
	private Long productId;
	private String orderTable;
	private String deliveryAdress;
	private String orderNumber;
	private String deliveryDate;

	public Long getOrderId() {
		return orderId;
	}

	public void setOrderId(Long orderId) {
		this.orderId = orderId;
	}

	public Long getUserId() {
		return userId;
	}

	public void setUserId(Long userId) {
		this.userId = userId;
	}

	public Long getProductId() {
		return productId;
	}

	public void setProductId(Long productId) {
		this.productId = productId;
	}

	public String getOrderTable() {
		return orderTable;
	}

	public void setOrderTable(String orderTable) {
		this.orderTable = orderTable;
	}

	public String getDeliveryAdress() {
		return deliveryAdress;
	}

	public void setDeliveryAdress(String deliveryAdress) {
		this.deliveryAdress = deliveryAdress;
	}

	public String getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(String orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getDeliveryDate() {
		return deliveryDate;
	}

	public void setDeliveryDate(String deliveryDate) {
		this.deliveryDate = deliveryDate;
	}

}
