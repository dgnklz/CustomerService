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

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.customerService.customerService.business.abstracts.CustomerService;
import com.customerService.customerService.core.utilities.results.DataResult;
import com.customerService.customerService.core.utilities.results.Result;
import com.customerService.customerService.entities.concretes.Customer;
import com.customerService.customerService.entities.concretes.CustomerPage;
import com.customerService.customerService.entities.concretes.CustomerSearchCriteria;

import lombok.extern.log4j.Log4j2;

@RestController
@RequestMapping("/customers")
@Log4j2
public class CustomerController {
	
	private CustomerService customerService;
	
	Logger logger = LoggerFactory.getLogger(CustomerController.class);
	
	@Autowired
	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}
	
//Without external service methods that return error message
	@GetMapping
	public List<Customer> getAllCustomers(){
		logger.info("[getAllCustomers] info message");
		logger.warn("[getAllCustomers] warn message");
		logger.error("[getAllCustomers] error message");
		log.debug("[getAllCustomers] debug message");
		logger.trace("[getAllCustomers] trace message");
		return customerService.getAllCustomers();
		
	}
	
	@GetMapping("/pagination")
	public ResponseEntity<Page<Customer>> getCustomersWithSort1(CustomerPage customerPage, CustomerSearchCriteria customerSearchCriteria){
		return new ResponseEntity<>(customerService.getCustomersWithSort1(customerPage, customerSearchCriteria), HttpStatus.OK);
	}
	
	@GetMapping("/{customerId}")
	public Customer getOneCustomer(@PathVariable Long customerId) {
		return customerService.getOneCustomer(customerId);
	}
	
	@PostMapping
	public Customer saveOneCustomer(@RequestBody Customer newCustomer) {
		return customerService.saveOneCustomer(newCustomer);
	}
	
	@DeleteMapping("/{customerId}")
	public void deleteOneCustomer(@PathVariable Long customerId) {
		customerService.deleteOneCustomer(customerId);
	}
	
	
	
//With external service methods that return error message
	@GetMapping("/ext/getall")
	public DataResult<List<Customer>> getAllCustomers1(){
		return customerService.getAllCustomers1();
	}
	
	@GetMapping("/ext/getone/{customerId}")
	public DataResult<Customer> getOneCustomer1(@PathVariable Long customerId) {
		return customerService.getOneCustomer1(customerId);
	}
	
	@PostMapping("/ext/create")
	public Result createCustomer1(@RequestBody Customer customer) {
		return customerService.saveOneCustomer1(customer);
	}
	
	@DeleteMapping("/ext/delete/{customerId}")
	public Result deleteOneCustomer1(@PathVariable Long customerId) {
		return customerService.deleteOneCustomer1(customerId);
	}
	
}
