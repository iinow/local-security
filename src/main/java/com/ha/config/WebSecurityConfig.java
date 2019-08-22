package com.ha.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.RememberMeServices;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.security.web.authentication.rememberme.PersistentTokenBasedRememberMeServices;
import org.springframework.security.web.authentication.rememberme.PersistentTokenRepository;

import com.ha.authentication.CalendarUserAuthenticationProvider;
import com.ha.authentication.filter.DomainUsernamePasswordAuthenticationFilter;
import com.ha.repository.RememberMeTokenRepository;
import com.ha.security.userdetails.CalendarUserDetailsService;

import lombok.extern.log4j.Log4j2;

@Log4j2(topic = "WebSecurityConfig")
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	CalendarUserDetailsService detailsService;
	
	@Autowired
	CalendarUserAuthenticationProvider provider;
	
	@Autowired
	RememberMeTokenRepository rememberMeTokenRepository;
	
	/**
	 * 
	 * provider: SecurityContextHolder 에 Authentication 으로 저장이 된다.
	 * */
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		//auth.parentAuthenticationManager(authenticationManager)
//		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
//		auth.jdbcAuthentication()
//			.passwordEncoder(encoder);
		auth.userDetailsService(detailsService);
		auth.authenticationProvider(provider);
	}
	
	@Bean
    @Override
    public AuthenticationManager authenticationManagerBean() throws Exception {
		//UserDetailsManager
        return super.authenticationManagerBean();
    }

	@Override
	protected UserDetailsService userDetailsService() {
		return this.detailsService;
	}
	
	@Override
	public void configure(WebSecurity web) throws Exception {
//		web.ignoring()
//			.antMatchers("/resources/**");
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/users/**").hasRole("ADMIN")
//			.antMatchers("/users/**").hasAuthority("ADMIN")
			.anyRequest().authenticated()
			.and().formLogin()
			.and().httpBasic()
			.and().logout()
//			.and().rememberMe()
//				.tokenRepository(rememberMeTokenRepository)
//				.alwaysRemember(true)
//				.tokenValiditySeconds(24*60*60)
//				.key("haha")
			.and().rememberMe()
				.rememberMeServices(rememberMeServices(rememberMeTokenRepository))
			.and().csrf().disable();
			
			// Add custom DomainUsernamePasswordAuthenticationFilter
        	//.addFilterAt(domainUsernamePasswordAuthenticationFilter(),
            //  UsernamePasswordAuthenticationFilter.class)
	}
	
	@Bean
	public RememberMeServices rememberMeServices(PersistentTokenRepository repository) {
		PersistentTokenBasedRememberMeServices myServices = new PersistentTokenBasedRememberMeServices("haha", detailsService, rememberMeTokenRepository);
		myServices.setAlwaysRemember(true);
		myServices.setTokenValiditySeconds(24 * 60 * 60);
		return myServices;
	}
	
	//@Bean
	public DomainUsernamePasswordAuthenticationFilter domainUsernamePasswordAuthenticationFilter() throws Exception {
		DomainUsernamePasswordAuthenticationFilter dupaf = new DomainUsernamePasswordAuthenticationFilter(super.authenticationManagerBean());
		dupaf.setFilterProcessesUrl("/login");
        dupaf.setUsernameParameter("username");
        dupaf.setPasswordParameter("password");

        dupaf.setAuthenticationSuccessHandler(new SavedRequestAwareAuthenticationSuccessHandler(){{
                setDefaultTargetUrl("/default");
            }}
        );

        dupaf.setAuthenticationFailureHandler(new SimpleUrlAuthenticationFailureHandler(){{
                setDefaultFailureUrl("/login/form?error");
            }}
        );

        dupaf.afterPropertiesSet();
        return dupaf;
	}
}

