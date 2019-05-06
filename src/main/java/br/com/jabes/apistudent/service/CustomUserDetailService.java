package br.com.jabes.apistudent.service;



import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import br.com.jabes.apistudent.model.User;
import br.com.jabes.apistudent.repository.UserRepository;

@Component
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private UserRepository uRepository ;

	@Override
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {
		
	 User user = Optional.ofNullable(uRepository.findByUsername(username))
			.orElseThrow(() -> new UsernameNotFoundException("User Not Found"));
		
	
	 List<GrantedAuthority> listautorizacaoAdmin= AuthorityUtils.createAuthorityList("ROLE_USER","ROLE_ADMIN");
	 List<GrantedAuthority> listautorizacaoUser= AuthorityUtils.createAuthorityList("ROLE_USER");
		return new org.springframework.security.core.userdetails.User(user.getUsername()
				,user.getPassword()
				,user.isAdmin() ? listautorizacaoAdmin : listautorizacaoUser);
		
	}

}
