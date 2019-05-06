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
		http.authorizeRequests()
		.anyRequest().authenticated()
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
	
//	@Autowired
//	public void configureGlobal(AuthenticationManagerBuilder managerBuilder) throws Exception{
//		managerBuilder.inMemoryAuthentication()
//		.withUser("jabes").password("1234").roles("USER")
//		.and()
//		.withUser("admin").password("1234").roles("USER","ADMIN");
//		
//	}
}
