package com.example.tabelog_nagoyameshi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.tabelog_nagoyameshi.entity.Role;

public interface RoleRepository extends JpaRepository<Role, Integer> {
	public Role findByRoleName2(String roleName2);

}