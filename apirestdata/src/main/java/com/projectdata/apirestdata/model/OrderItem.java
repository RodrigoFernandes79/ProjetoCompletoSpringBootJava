package com.projectdata.apirestdata.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projectdata.apirestdata.model.pk.OrderItemPK;
@Entity(name = "order_item")
public class OrderItem implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	@EmbeddedId
	private OrderItemPK id =new OrderItemPK();
	
	private Integer quantity;
	
	private Double price;
	
	
	public OrderItem() {
		super();
	}

	
	public OrderItem(Order order,Product product, Integer quantity, Double price) {
		super();
		id.setOrder(order);
		id.setProduct(product);
		this.quantity = quantity;
		this.price = price;
	}

	@JsonBackReference
	public Order getOrder() {
		return  id.getOrder();
		
	}
	
	
	public void setOrder(Order order) {
		 id.setOrder(order);
		
	}
	
	public Product getProduct() {
		 return id.getProduct();
		
	}
	public void setProduct(Product product) {
		id.setProduct(product);
	}

	public Integer getQuantity() {
		return quantity;
	}

	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
	
	

}
