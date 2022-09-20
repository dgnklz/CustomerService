package com.customerService.customerService.business.concretes;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;

import com.customerService.customerService.business.abstracts.CustomerService;
import com.customerService.customerService.core.utilities.results.DataResult;
import com.customerService.customerService.core.utilities.results.ErrorDataResult;
import com.customerService.customerService.core.utilities.results.ErrorResult;
import com.customerService.customerService.core.utilities.results.Result;
import com.customerService.customerService.core.utilities.results.SuccessDataResult;
import com.customerService.customerService.core.utilities.results.SuccessResult;
import com.customerService.customerService.dataAccess.abstracts.CustomerDao;
import com.customerService.customerService.dataAccess.concretes.CustomerCriteriaRepository;
import com.customerService.customerService.entities.concretes.Customer;
import com.customerService.customerService.entities.concretes.CustomerPage;
import com.customerService.customerService.entities.concretes.CustomerSearchCriteria;


@Service
public class CustomerManager implements CustomerService{
	
	private CustomerDao customerDao;
	private CustomerCriteriaRepository customerCriteriaRepository;
	
	@Autowired
	public CustomerManager(CustomerDao customerDao, CustomerCriteriaRepository customerCriteriaRepository) {
		this.customerDao = customerDao;
		this.customerCriteriaRepository = customerCriteriaRepository;
	}

//Without external service methods that return error message
	@Override
	public List<Customer> getAllCustomers() {
		return customerDao.findAll();
	}
	
	@Override
	public Page<Customer> getCustomersWithSort1(CustomerPage customerPage,
			CustomerSearchCriteria customerSearchCriteria) {
		return customerCriteriaRepository.findAllWithFilters(customerPage, customerSearchCriteria);
	}
	
	@Override
	public Customer getOneCustomer(Long customerId) {
		return customerDao.findById(customerId).orElse(null);
	}

	@Override
	public Customer saveOneCustomer(Customer newCustomer) {
		return customerDao.save(newCustomer);
	}

	@Override
	public void deleteOneCustomer(Long customerId) {
		customerDao.deleteById(customerId);
	}
	
	
	
//With external service methods that return error message
	@Override
	public DataResult<List<Customer>> getAllCustomers1() {
		if(new SuccessDataResult<List<Customer>>(true, customerDao.findAll()).isSuccess()) {
			return new SuccessDataResult<List<Customer>>(true, customerDao.findAll());
		}else {
			return new ErrorDataResult<List<Customer>>(false, "There is no any customer in the system yet");
		}
	}

	@Override
	public DataResult<Customer> getOneCustomer1(Long customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(customer.isPresent()) {
			return new SuccessDataResult<Customer>(true, customer.get());
		}
		else {
			return new ErrorDataResult<Customer>(false, "The Customer could not be found");
		}
	}

	@Override
	public Result deleteOneCustomer1(Long customerId) {
		Optional<Customer> customer = customerDao.findById(customerId);
		if(customer.isPresent()) {
			customerDao.deleteById(customerId);
			return new SuccessResult(true, "The customer is deleted");
		}
		else {
			return new ErrorResult(false, "The customer to be deleted could not be found");
		}
	}

	@Override
	public Result saveOneCustomer1(Customer customer) {
		if(customer.getCustomerName().isEmpty() || customer.getCustomerLastName().isEmpty()) {
			return new ErrorResult(false, "The customer could not be added");
		}
		else {
			customerDao.save(customer);
			return new SuccessResult(true, "The customer is added");
		}
	}

}
