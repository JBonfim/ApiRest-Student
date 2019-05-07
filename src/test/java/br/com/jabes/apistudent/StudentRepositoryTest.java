package br.com.jabes.apistudent;

import javax.validation.ConstraintViolationException;

import org.assertj.core.api.Assertions;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.jabes.apistudent.model.Student;
import br.com.jabes.apistudent.repository.StudentRepository;



/**
 * @author Jabes
 *
 */
@RunWith(SpringRunner.class)
@DataJpaTest
//Para utilizar o banco de dados real da aplicação em ambiente de teste
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository stRepository;
	
	@Rule
	public ExpectedException thrown = ExpectedException.none();
	
	@Test
	public void createShouldPersistData(){
		Student student = new Student("Julia", "julia@email.com");
		this.stRepository.save(student);
		Assertions.assertThat(student.getId()).isNotNull();
		Assertions.assertThat(student.getName()).isEqualTo("Julia");
		Assertions.assertThat(student.getEmail()).isEqualTo("julia@email.com");
		
	}
	
	@Test
	public void deleteShouldPersistData(){
		Student student = new Student("Julia", "julia@email.com");
		this.stRepository.save(student);
		this.stRepository.delete(student);
		Assertions.assertThat(this.stRepository.findById(student.getId())).isNull();;
		
	}
	
	@Test
	public void updateShouldChangeAndPersistData(){
		Student student = new Student("Julia", "julia@email.com");
		student = this.stRepository.save(student);
		student.setName("Julia Rodrigues");
		student.setEmail("juliarodrigues@email.com");
		this.stRepository.save(student);
		student = this.stRepository.findById(student.getId());
		Assertions.assertThat(student.getName()).isEqualTo("Julia Rodrigues");
		Assertions.assertThat(student.getEmail()).isEqualTo("juliarodrigues@email.com");
		
	}
	
	@Test
	public void createWhenNameIsNullShouldThrowConstraintViolationException(){
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("O campo nome é obrigatório");
		this.stRepository.save(new Student());
	}
	@Test
	public void createWhenEmailIsNullShouldThrowConstraintViolationException(){
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("o campo email é obrigatório");
		Student student = new Student("Julia", "");
		this.stRepository.save(student);
	}
	@Test
	public void createWhenEmailNotValidldThrowConstraintViolationException(){
		thrown.expect(ConstraintViolationException.class);
		thrown.expectMessage("Email inválido.");
		Student student = new Student("Julia", "email");
		this.stRepository.save(student);
	}
	

}
