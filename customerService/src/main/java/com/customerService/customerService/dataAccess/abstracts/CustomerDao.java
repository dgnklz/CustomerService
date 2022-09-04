package com.customerService.customerService.dataAccess.abstracts;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.customerService.customerService.core.utilities.results.DataResult;
import com.customerService.customerService.core.utilities.results.Result;
import com.customerService.customerService.entities.concretes.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>, CrudRepository<Customer, Long>{
	//JpaRepository brings auto methods like delete, change, update, bring all, find by id
	
//	@Query(value="SELECT * FROM customers")
//	DataResult<List<Customer>> getAllCustomers();
//	
//	@Query(value="SELECT customer_name FROM customers WHERE id = customerId ")
//	DataResult<Customer> getOneCustomer (Long customerId);
//	
//	@Query(value="DELETE FROM customers WHERE id = customerId")
//	void deleteById(Long customerId);
//	
//	@Query(value="INSERT INTO customers (customerName, customerLastName)")
//	Result saveOneCustomer(Customer customer);
}
