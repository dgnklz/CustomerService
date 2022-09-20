package com.customerService.customerService.dataAccess.abstracts;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;


import com.customerService.customerService.entities.concretes.Customer;

public interface CustomerDao extends JpaRepository<Customer, Long>, CrudRepository<Customer, Long>{
	//JpaRepository brings auto methods like delete, change, update, bring all, find by id

}
