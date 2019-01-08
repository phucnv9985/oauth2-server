package com.example.oauth2server.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()

				/*
				 * allow any user to access the main page, the login page, and the resources
				 * pages
				 */
				.antMatchers("/", "/login", "/resources/**").permitAll()

				/*
				 * require that any user who access vendor pages should have a role
				 * "ROLE_VENDOR"
				 */
				.antMatchers("/vendor/**").hasRole("VENDOR")

				/*
				 * configure that a user with either role "ROLE_CUSTOMER" or "ROLE_VENDOR" will
				 * have access to the customer pages
				 */
				.antMatchers("/customer/**").hasAnyRole("CUSTOMER, VENDOR")

				.and()

				/*
				 * automatically generate the log in page
				 */
				.formLogin();
	}
    @Autowired
	public void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
				/*
				 * configure user "vendor" with password "vendor" and role "ROLE_VENDOR"
				 */
				.withUser("vendor").password("vendor").roles("VENDOR").and()
				/*
				 * configure user "customer" with password "customer" and role "ROLE_CUSTOMER"
				 */
				.withUser("customer").password("customer").roles("CUSTOMER");
	}
}
