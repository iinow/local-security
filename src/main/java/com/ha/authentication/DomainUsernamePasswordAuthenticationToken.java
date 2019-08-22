package com.ha.authentication;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import com.ha.entity.CalendarUser;

import lombok.Getter;

@Getter
public final class DomainUsernamePasswordAuthenticationToken extends UsernamePasswordAuthenticationToken {
	private static final long serialVersionUID = -7483681209672584559L;

	private String domain;
	
	public DomainUsernamePasswordAuthenticationToken(String principal, String credentials, String domain) {
        super(principal, credentials);
        this.domain = domain;
    }

    public DomainUsernamePasswordAuthenticationToken(CalendarUser principal, String credentials, String domain,
            Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
        this.domain = domain;
    }
}
