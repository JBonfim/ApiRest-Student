package br.com.jabes.apistudent.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.jabes.apistudent.erro.ResourceNotFoundException;
import br.com.jabes.apistudent.model.Student;
import br.com.jabes.apistudent.repository.StudentRepository;


/**
 * @author Jabes
 *
 */
@RestController
@RequestMapping("v1")
public class StudentResource {
	
	@Autowired
	private StudentRepository sRepository;

	@GetMapping(path="protected/students")
	public ResponseEntity<?> listAll(Pageable pageable){
		return new ResponseEntity<>(sRepository.findAll(pageable),HttpStatus.OK);
	}
	
	@GetMapping( path="protected/students/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") long id){
		verifyIfStudentExists(id);
		Student student = sRepository.findById(id);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	
	@PostMapping(path="admin/students")
	public ResponseEntity<?> save(@Validated @RequestBody Student student){
		sRepository.save(student);
		return new ResponseEntity<>(student,HttpStatus.OK);
	}
	@DeleteMapping(path="admin/students/{id}")
	public ResponseEntity<?> delete(@PathVariable(name="id") long id){
		verifyIfStudentExists(id);
		sRepository.delete(id);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	@PutMapping(path="admin/students")
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
