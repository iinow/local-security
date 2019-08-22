package com.ha.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.entity.RememberMe;

public interface RememberMeRepository extends JpaRepository<RememberMe, String>{

	RememberMe findOneByUsername(String username);
}
