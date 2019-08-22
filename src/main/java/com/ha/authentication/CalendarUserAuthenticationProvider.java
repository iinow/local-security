package com.ha.authentication;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.stereotype.Component;

import com.ha.common.CalendarUserAuthorityUtils;
import com.ha.entity.CalendarUser;
import com.ha.service.CalendarService;

@Component
public class CalendarUserAuthenticationProvider implements AuthenticationProvider {

	@Autowired
	private CalendarService service;
	
	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		UsernamePasswordAuthenticationToken token = (UsernamePasswordAuthenticationToken) authentication;
		String username = token.getName();
//		String domain = token.getDomain();
//		String email = username + "@" + domain;
        CalendarUser user = username == null ? null : service.findUserByEmail(username);
        if(user == null) {
            throw new UsernameNotFoundException("Invalid username/password");
        }
        String password = user.getPassword().replace("{noop}", "");
        if(!password.equals(token.getCredentials())) {
            throw new BadCredentialsException("Invalid username/password");
        }
        Collection<? extends GrantedAuthority> authorities = CalendarUserAuthorityUtils.createAuthorities(user);
        return new UsernamePasswordAuthenticationToken(user, password, authorities);
	}

	@Override
	public boolean supports(Class<?> authentication) {
		return UsernamePasswordAuthenticationToken.class.equals(authentication);
	}
}
