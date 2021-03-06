package br.com.jabes.apistudent.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import br.com.jabes.apistudent.service.CustomUserDetailService;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled=true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private CustomUserDetailService customUser;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		// TODO Auto-generated method stub
		//.anyRequest().authenticated()
		http.authorizeRequests()
		.antMatchers("/*/protected/**").hasRole("USER")
		.antMatchers("/*/admin/**").hasRole("ADMIN")
		.and()
		.httpBasic()
		.and()
		.csrf().disable();
	}
	
	@Override
	protected void configure(AuthenticationManagerBuilder auth)
			throws Exception {
		auth.userDetailsService(customUser).passwordEncoder(new BCryptPasswordEncoder());
		
	}
	
}
