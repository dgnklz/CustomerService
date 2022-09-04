package com.customerService.customerService.business.abstracts;

import java.util.List;

import org.springframework.data.domain.Page;
import com.customerService.customerService.core.utilities.results.DataResult;
import com.customerService.customerService.core.utilities.results.Result;
import com.customerService.customerService.entities.concretes.Customer;
import com.customerService.customerService.entities.concretes.CustomerPage;
import com.customerService.customerService.entities.concretes.CustomerSearchCriteria;

public interface CustomerService {
	
	DataResult<List<Customer>> getAllCustomers();
	
	Page<Customer> getCustomersWithSort(CustomerPage customerPage, CustomerSearchCriteria customerSearchCriteria);

	DataResult<Customer> getOneCustomer (Long customerId);

	Result saveOneCustomer(Customer customer);

	Result deleteById(Long customerId);
	
}
