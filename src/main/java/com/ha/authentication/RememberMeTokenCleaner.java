package com.ha.authentication;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.ha.entity.RememberMe;
import com.ha.repository.RememberMeRepository;

import lombok.extern.log4j.Log4j2;

@Log4j2(topic = "RememberMeTokenCleaner")
public class RememberMeTokenCleaner implements Runnable {
	private long tokenValidityInMs;
	private RememberMeRepository repository;
	
	public RememberMeTokenCleaner(RememberMeRepository repository, long tokenValidityInMs) {
		this.tokenValidityInMs = tokenValidityInMs;
		this.repository = repository;
	}
	
	
	@Override
	public void run() {
		long expiredInMs = System.currentTimeMillis() - tokenValidityInMs;
		log.info("Searching for persistent logins older than {}ms", tokenValidityInMs);
		
		try {
            List<RememberMe> expired = repository.findByLastUsedBefore(new Date(expiredInMs));
            for(RememberMe me: expired){
                log.info("*** Removing persistent login for {} ***", me.getUsername());
                repository.delete(me);
            }
        } catch(Throwable t) {
            log.error("**** Could not clean up expired persistent remember me tokens. ***", t);
        }
	}
}
