package com.ha.service;

import com.ha.entity.CalendarUser;

public interface UserContext {

	CalendarUser getCurrentUser();
	
	void setCurrentUser(final CalendarUser user);
}
