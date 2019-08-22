package com.ha.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ha.entity.CalendarUser;
import com.ha.service.CalendarService;

@RestController
@RequestMapping(path = "/users")
public class CalendarUserController {
	
	@Autowired
	private CalendarService service;

	@GetMapping("/{email}")
	public CalendarUser getCalendarUser(
			@PathVariable(name = "email", required = true) String email) {
		return service.findUserByEmail(email);
	}
}
