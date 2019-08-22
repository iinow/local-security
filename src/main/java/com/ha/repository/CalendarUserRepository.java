package com.ha.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.ha.entity.CalendarUser;

public interface CalendarUserRepository extends JpaRepository<CalendarUser, Integer>{

	CalendarUser findOneByEmail(String email);
}
