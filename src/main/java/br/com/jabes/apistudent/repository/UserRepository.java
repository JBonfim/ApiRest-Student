package br.com.jabes.apistudent.repository;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.jabes.apistudent.model.User;

public interface UserRepository  extends PagingAndSortingRepository<User, Long> {
	User findByUsername(String username);
}
