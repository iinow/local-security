package com.ha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.SecurityConfig;

import com.ha.authentication.RememberMeTokenCleaner;
import com.ha.repository.RememberMeRepository;

@Configuration
@EnableScheduling
public class JavaConfig {
	
	@Autowired
	private RememberMeRepository repository;

	/**
	 * @param fixedRate = 주기
	 * @param RememberMeTokenCleaner.tokenValidityInMs = RememberMe.lastUsed 값 보다 이후이면 삭제처리한다.  
	 * 
	 * 10초마다 스레드가 돌고 RememberMeToken 에 lastUsed 값을 확인해서 60초가 지난 것인 경우 삭제
	 * */
	@Scheduled(fixedRate = 10_000)
	public void tokenRepositoryCleaner() {
		Thread thread = new Thread(new RememberMeTokenCleaner(repository, 60_000L));
		thread.start();
	}
}
