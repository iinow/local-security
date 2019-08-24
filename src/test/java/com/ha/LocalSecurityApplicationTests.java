package com.ha;

import static org.junit.Assert.*;

import java.util.Base64;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.token.Sha512DigestUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;

//import org.springframework.security.authentication.encoding.ShaPasswordEncoder;

@RunWith(SpringRunner.class)
@SpringBootTest
public class LocalSecurityApplicationTests {

	@Test
	public void contextLoads() {
	}
	
//	@Test
//	public void password암호화() throws InterruptedException {
//		String password = "hi";
//		byte[] encode = Sha512DigestUtils.sha(password);
//		String res = new String(Base64.getEncoder().encode(encode));
//		System.out.println(res);
//	}
//	
//	@Test
//	public void BCryptPassword() throws InterruptedException {
//		String password = "hi";
//		
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		String encode1 = encoder.encode(password);
//		System.out.println(encode1);
//		
//		Thread.sleep(1000);
//		
//		String encode2 = encoder.encode(password);
//		System.out.println(encode2);
//		boolean match = encoder.matches(password, encode2);
//		
//		System.out.println("결과값 : "+match);
//	}
}
