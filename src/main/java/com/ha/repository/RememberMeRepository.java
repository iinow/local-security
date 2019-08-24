package com.ha.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ha.entity.RememberMe;

public interface RememberMeRepository extends JpaRepository<RememberMe, String>{
	RememberMe findOneByUsername(String username);
	List<RememberMe> findByLastUsedBefore(Date expiration);
}
