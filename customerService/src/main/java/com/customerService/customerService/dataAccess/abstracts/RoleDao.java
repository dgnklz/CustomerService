package com.customerService.customerService.dataAccess.abstracts;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.customerService.customerService.entities.concretes.ERole;
import com.customerService.customerService.entities.concretes.Role;

@Repository
public interface RoleDao extends JpaRepository<Role, Integer>{
	Optional<Role> findByName(ERole name);
}
