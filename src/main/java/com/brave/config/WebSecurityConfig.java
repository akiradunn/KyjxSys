package com.brave.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
	@Autowired
	@Qualifier("myuserDetailsService")
	private MyUserDetailsService myuserDetailsService;
	@Autowired
	@Qualifier("authenticationSuccessHandler")
	private SecurityLoginSuccessHandler authenticationSuccessHandler;
	@Autowired
	@Qualifier("authenticationFailureHandler")
	private AuthenticationFailureHandler authenticationFailureHandler;

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/verifyLogin","/login.html","/api/**").permitAll().anyRequest()
				.authenticated()
				.and().formLogin().loginPage("/login.html")
				.loginProcessingUrl("/verifyLogin").successHandler(authenticationSuccessHandler)
				.failureHandler(authenticationFailureHandler).and().csrf().disable().logout().permitAll();
	}

	@Override
	public void configure(WebSecurity web) throws Exception {
		web.ignoring().antMatchers("/files/**", "/bootstrap-3.3.7/**");
	}

	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(myuserDetailsService);
	}

}