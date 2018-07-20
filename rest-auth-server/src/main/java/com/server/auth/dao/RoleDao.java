package com.server.auth.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.server.auth.entity.Role;

public interface RoleDao extends PagingAndSortingRepository<Role, String>{
	
	
	
}