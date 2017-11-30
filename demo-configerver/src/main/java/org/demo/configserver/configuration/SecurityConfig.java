package org.demo.configserver.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

import lombok.Data;

@Configuration
@EnableWebSecurity
@Data
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	private static final String ROLE_USER = "USER";
	
	@Value("${config.user}")
	private String username;
	
	@Value("${config.pass}")
	private String password;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
			.antMatchers("/encrypt").permitAll()
			.anyRequest().authenticated()
			.and()
			.httpBasic()
			.and()
			.csrf().disable();
	}

	@Autowired
	protected void globalConfigure(AuthenticationManagerBuilder auth) throws Exception {
		auth.inMemoryAuthentication()
			.withUser(getUsername()).password(getPassword())
			.roles(ROLE_USER);
	}
	
}
