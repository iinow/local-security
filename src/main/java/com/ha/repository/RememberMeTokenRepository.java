package com.ha.repository;

import java.util.Date;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.rememberme.PersistentRememberMeToken;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.ha.entity.RememberMe;

@Repository
public class RememberMeTokenRepository implements PersistentTokenRepository {

	@Autowired
	private RememberMeRepository repository;
	
	@Transactional
	@Override
	public void createNewToken(PersistentRememberMeToken token) {
		String ipSeries = ipSeries(token.getSeries());
		RememberMe me = new RememberMe();
		me.setId(ipSeries);
		me.setLastUsed(token.getDate());
		me.setToken(token.getTokenValue());
		me.setUsername(token.getUsername());
		
		repository.save(me);
	}

	@Transactional
	@Override
	public void updateToken(String series, String tokenValue, Date lastUsed) {
		String ipSeries = ipSeries(series);
		RememberMe me = repository.findById(ipSeries).orElseThrow();
		me.setToken(tokenValue);
		me.setLastUsed(lastUsed);
	}

	@Override
	public PersistentRememberMeToken getTokenForSeries(String seriesId) {
		String ipSeries = ipSeries(seriesId);
		RememberMe me = repository.findById(ipSeries).orElseThrow();
		PersistentRememberMeToken token = new PersistentRememberMeToken(me.getUsername(), me.getId(), me.getToken(), me.getLastUsed());
		return token;
	}

	@Override
	public void removeUserTokens(String username) {
		RememberMe me = repository.findOneByUsername(username);
		repository.deleteById(me.getId());
	}
	
	private String ipSeries(String series) {
        ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        if (attributes == null) {
            throw new IllegalStateException("RequestContextHolder.getRequestAttributes() cannot be null");
        }
        String remoteAddr = attributes.getRequest().getRemoteAddr();
        //logger.debug("Remote address is {}", remoteAddr);

        return series + remoteAddr;
    }
}
