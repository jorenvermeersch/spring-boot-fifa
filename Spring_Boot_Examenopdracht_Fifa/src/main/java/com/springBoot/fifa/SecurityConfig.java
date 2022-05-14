package com.springBoot.fifa;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	private static final String ROLE_USER = "USER", ROLE_ADMIN = "ADMIN";

	@Autowired
	private DataSource dataSource;

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.jdbcAuthentication().dataSource(dataSource);
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// Everyone can log in.
		// Users can only access overview. Admins can access everything.
		http.authorizeRequests().antMatchers("/403*").permitAll().antMatchers("/fifa").hasRole(ROLE_USER)
				.antMatchers("/fifa/**").hasRole(ROLE_ADMIN);

		// Everyone can access login page. Set up `Access denied` page.
		http.formLogin().defaultSuccessUrl("/fifa", true).loginPage("/login").permitAll().and().exceptionHandling()
				.accessDeniedPage("/403").and().csrf();

		// Everyone can log out.
		http.logout().permitAll();
	}

}
