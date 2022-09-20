package com.customerService.customerService.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;

import com.customerService.customerService.core.utilities.results.DataResult;
import com.customerService.customerService.core.utilities.results.Result;
import com.customerService.customerService.entities.concretes.Customer;
import com.customerService.customerService.entities.concretes.CustomerPage;
import com.customerService.customerService.entities.concretes.CustomerSearchCriteria;

public interface CustomerService {

//Without external service methods that return error message
	public List<Customer> getAllCustomers();
	
	Page<Customer> getCustomersWithSort1(CustomerPage customerPage, CustomerSearchCriteria customerSearchCriteria);
	
	public Customer getOneCustomer(Long customerId);
	
	public Customer saveOneCustomer(Customer newCustomer);
	
	public void deleteOneCustomer(Long customerId);
	
	
	
//With external service methods that return error message
	DataResult<List<Customer>> getAllCustomers1();

	DataResult<Customer> getOneCustomer1(Long customerId);

	Result saveOneCustomer1(Customer customer);

	Result deleteOneCustomer1(Long customerId);
	
}
