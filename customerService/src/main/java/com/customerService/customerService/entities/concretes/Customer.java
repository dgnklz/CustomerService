package com.customerService.customerService.entities.concretes;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.customerService.customerService.entities.abstracts.IEntity;

import lombok.Data;


@Entity
@Table(name="customers")
@Data
public class Customer implements IEntity{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	long id;
	
	String customerName;
	String customerLastName;
	
	public Customer() {
		
	}
	
	public Customer(String customerName, String customerLastName) {
		this.customerName = customerName;
		this.customerLastName = customerLastName;
	}
	
}
