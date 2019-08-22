package com.ha;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

import com.ha.entity.CalendarUser;
import com.ha.service.DefaultCalendarService;

@EnableWebSecurity(debug = false)
@SpringBootApplication
public class LocalSecurityApplication {

	public static void main(String[] args) {
		SpringApplication.run(LocalSecurityApplication.class, args);
	}

	@Autowired
	DefaultCalendarService service;
	
//	@PostConstruct
	private void init() {
		CalendarUser user = new CalendarUser();
		user.setEmail("admin@haha.com");
		user.setPassword("{noop}admin");
		user.setFirstName("Ha");
		user.setLastName("Ha");
		service.createUser(user);
		
		CalendarUser u = service.findUserByEmail("admin@haha.com");
		System.out.println("HAHAHAHAHA: "+u.toString());
	}
}
