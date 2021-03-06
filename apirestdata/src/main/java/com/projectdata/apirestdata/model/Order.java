package com.projectdata.apirestdata.model;

import java.io.Serializable;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.projectdata.apirestdata.model.enums.OrderStatus;


@Entity(name = "orders")
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private Instant date;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User client;

	@Enumerated(EnumType.STRING)
	@Column
	private OrderStatus orderStatus;
	@JsonManagedReference
	@OneToMany(mappedBy="id.order")
	private Set<OrderItem> items = new HashSet<>();
	@JsonManagedReference
	@OneToOne(mappedBy="order",cascade =CascadeType.ALL)
	private Payment payment;


	public Order(Long id, Instant date, User client, OrderStatus orderStatus, Set<OrderItem> items, Payment payment) {
		super();
		this.id = id;
		this.date = date;
		this.client = client;
		this.orderStatus = orderStatus;
		this.items = items;
		this.payment = payment;
	}

	public Order() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Instant getDate() {
		return date;
	}

	public void setDate(Instant date) {
		this.date = date;
	}

	public User getClient() {
		return client;
	}

	public void setClient(User client) {
		this.client = client;
	}

	public OrderStatus getOrderStatus() {
		return orderStatus;
	}

	public void setOrderStatus(OrderStatus orderStatus) {
		this.orderStatus = orderStatus;
	}

	public Set<OrderItem> getItems() {
		return items;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Double getTotal() {
		double sum = 0;
		for(OrderItem x :items) {
	
		sum= sum +x.getSubTotal();
		}
		return sum;
		
	}
	
}

