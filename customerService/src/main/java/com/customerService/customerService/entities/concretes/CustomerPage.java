package com.customerService.customerService.entities.concretes;

import org.springframework.data.domain.Sort;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CustomerPage {
	
	private int pageNumber = 0;
	private int pageSize = 10;
	private Sort.Direction sortDirection = Sort.Direction.ASC;
	private String sortBy = "lastName";
	
}
