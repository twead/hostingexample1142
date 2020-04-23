package com.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@EnableGlobalMethodSecurity(securedEnabled = true)
@Configuration
public class SecurityConf extends WebSecurityConfigurerAdapter {
	
	/*
	@Autowired
	public void configureAuth(AuthenticationManagerBuilder auth) throws Exception{
		auth
		  .inMemoryAuthentication()
		    .withUser("user") 
		    .password("pass")
		    .roles("USER")
		   .and()
             .withUser("admin")
             .password("pass")
             .roles("ADMIN");
	}
	*/


	private final UserDetailsService userService;
	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	public SecurityConf(UserDetailsService userService, BCryptPasswordEncoder bCryptPasswordEncoder) {
	       this.userService = userService;
	       this.bCryptPasswordEncoder = bCryptPasswordEncoder;
	}

	
	@Override
    public void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService).passwordEncoder(bCryptPasswordEncoder);
    }

	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.authorizeRequests()
				.antMatchers("/admin/**").hasRole("ADMIN")
				.antMatchers("/bloggers").hasRole("ADMIN")
				.antMatchers("/exercise").hasAnyRole("USER","ADMIN")
				.antMatchers("/registration").permitAll()
				.antMatchers("/reg").permitAll()
				.antMatchers("/forgot").permitAll()
				.antMatchers("/reset").permitAll()
				.antMatchers("/login").permitAll()
				.antMatchers("/activation/**").permitAll()
				.anyRequest().authenticated()
				.and()
			.formLogin()
				.loginPage("/login")
				.permitAll()
				.and()
			.logout()
				.logoutSuccessUrl("/login?logout")
				.permitAll();
	}

	
}
