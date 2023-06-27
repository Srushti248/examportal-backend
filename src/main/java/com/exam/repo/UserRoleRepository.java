package com.exam.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.exam.model.Role;

public interface UserRoleRepository extends JpaRepository<Role, Integer>{

	void save(String roleName);

}
