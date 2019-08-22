package com.ha.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ha.entity.CalendarUser;
import com.ha.entity.Event;
import com.ha.repository.CalendarUserRepository;

@Service
public class DefaultCalendarService implements CalendarService {

	@Autowired
	private CalendarUserRepository repository;
	
	@Override
	public CalendarUser getUser(int id) {
		return repository.findById(id).orElseThrow();
	}

	@Override
	public CalendarUser findUserByEmail(String email) {
		return repository.findOneByEmail(email);
	}

	@Override
	public List<CalendarUser> findUsersByEmail(String partialEmail) {
		return null;
	}

	@Override
	public int createUser(CalendarUser user) {
		return repository.save(user).getId();
	}

	@Override
	public Event getEvent(int eventId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int createEvent(Event event) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public List<Event> findForUser(int userId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Event> getEvents() {
		// TODO Auto-generated method stub
		return null;
	}

}
