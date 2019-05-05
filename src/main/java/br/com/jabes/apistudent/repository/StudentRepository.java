package br.com.jabes.apistudent.repository;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import br.com.jabes.apistudent.model.Student;

public interface StudentRepository extends PagingAndSortingRepository<Student, Long> {
	List<Student> findByName(String name);
	Student findById(long id);
}
