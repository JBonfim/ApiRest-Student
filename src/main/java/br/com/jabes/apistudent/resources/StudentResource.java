package br.com.jabes.apistudent.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import br.com.jabes.apistudent.erro.CustomErrorType;
import br.com.jabes.apistudent.erro.ResourceNotFoundException;
import br.com.jabes.apistudent.model.Student;
import br.com.jabes.apistudent.repository.StudentRepository;


/**
 * @author Jabes
 *
 */
@RestController
@RequestMapping("/students")
public class StudentResource {
	
	@Autowired
	private StudentRepository sRepository;

	@GetMapping
	public ResponseEntity<?> listAll(){
		return new ResponseEntity<>(sRepository.findAll(),HttpStatus.OK);
	}
	
	@GetMapping( path="/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") long id){
		verifyIfStudentExists(id);
		Student student = sRepository.findById(id);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	@PostMapping
	@Transactional
	public ResponseEntity<?> save(@Validated @RequestBody Student student){
		sRepository.save(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	@DeleteMapping
	public ResponseEntity<?> delete(@RequestBody Student student){
		verifyIfStudentExists(student.getId());
		sRepository.delete(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping
	public ResponseEntity<?> update(@RequestBody Student student){
		verifyIfStudentExists(student.getId());
		sRepository.save(student);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	private void verifyIfStudentExists(long id){
		if(sRepository.findById(id) == null)
			throw new ResourceNotFoundException("Studet Not Found for Id: " + id);
	}
}
