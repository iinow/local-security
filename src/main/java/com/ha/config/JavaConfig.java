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
	 * @param fixedRate = �ֱ�
	 * @param RememberMeTokenCleaner.tokenValidityInMs = RememberMe.lastUsed �� ���� �����̸� ����ó���Ѵ�.  
	 * 
	 * 10�ʸ��� �����尡 ���� RememberMeToken �� lastUsed ���� Ȯ���ؼ� 60�ʰ� ���� ���� ��� ����
	 * */
	@Scheduled(fixedRate = 10_000)
	public void tokenRepositoryCleaner() {
		Thread thread = new Thread(new RememberMeTokenCleaner(repository, 60_000L));
		thread.start();
	}
}
