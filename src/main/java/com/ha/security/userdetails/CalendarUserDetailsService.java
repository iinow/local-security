package com.ha.security.userdetails;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.ha.common.CalendarUserAuthorityUtils;
import com.ha.entity.CalendarUser;
import com.ha.service.CalendarService;

@Component
public class CalendarUserDetailsService implements UserDetailsService {

	@Autowired
	private CalendarService service;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		CalendarUser user = service.findUserByEmail(username);
		if (user == null) {
            throw new UsernameNotFoundException("Invalid username/password.");
        }
		return new CalendarUserDetails(user);
	}
	
	private final class CalendarUserDetails extends CalendarUser implements UserDetails {
		private static final long serialVersionUID = 3041798233566076951L;

		CalendarUserDetails(CalendarUser user) {
            setId(user.getId());
            setEmail(user.getEmail());
            setFirstName(user.getFirstName());
            setLastName(user.getLastName());
            setPassword(user.getPassword());
        }

        @Override
        public Collection<? extends GrantedAuthority> getAuthorities() {
            return CalendarUserAuthorityUtils.createAuthorities(this);
        }

		@Override
		public String getUsername() {
			return getEmail();
		}

		@Override
		public boolean isAccountNonExpired() {
			return true;
		}

		@Override
		public boolean isAccountNonLocked() {
			return true;
		}

		@Override
		public boolean isCredentialsNonExpired() {
			return true;
		}

		@Override
		public boolean isEnabled() {
			return true;
		}
	}
}
