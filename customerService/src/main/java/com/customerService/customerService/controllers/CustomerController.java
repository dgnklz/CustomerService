package com.customerService.customerService.controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.customerService.customerService.business.abstracts.CustomerService;
import com.customerService.customerService.core.utilities.results.DataResult;
import com.customerService.customerService.core.utilities.results.Result;
import com.customerService.customerService.entities.concretes.Customer;
import com.customerService.customerService.entities.concretes.CustomerPage;
import com.customerService.customerService.entities.concretes.CustomerSearchCriteria;

@RestController
@RequestMapping("/customers")
public class CustomerController {
	
	private CustomerService customerService;
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
	@GetMapping
	public DataResult<List<Customer>> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<Page<Customer>> getCustomersWithSort(CustomerPage customerPage, CustomerSearchCriteria customerSearchCriteria){
		return new ResponseEntity<>(customerService.getCustomersWithSort(customerPage, customerSearchCriteria), HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public DataResult<Customer> getOneCustomer(@PathVariable Long customerId) {
		return customerService.getOneCustomer(customerId);
	}
	
	@PostMapping
	public Result createCustomer(@RequestBody Customer customer) {
		return customerService.saveOneCustomer(customer);
	}
	
	@DeleteMapping("/{customerId}")
	public Result deleteOneCustomer(@PathVariable Long customerId) {
		return customerService.deleteById(customerId);
	}
	
}
