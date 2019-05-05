package br.com.jabes.apistudant.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.jabes.apistudant.model.Student;

public interface StudentRepository extends JpaRepository<Student, Long> {
	List<Student> findByName(String name);
	Student findById(long id);
}
