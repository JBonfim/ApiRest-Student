package br.com.jabes.apistudent;

import static java.util.Arrays.asList;

import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import br.com.jabes.apistudent.model.Student;
import br.com.jabes.apistudent.repository.StudentRepository;

/**
 * @author Jabes
 *Teste das requisições utilizando TestRestTemplate e MockMvc
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
public class StudentEndpointTest {
	@Autowired
	private TestRestTemplate restTemplate;
	
	@LocalServerPort
	private int port;
	
	@MockBean
	private StudentRepository stRepository;
	
	@Autowired
	private MockMvc mockmvc;
	
		
	
	@TestConfiguration
	static class Config{
		@Bean
		public RestTemplateBuilder restTemplateBuilder(){
			return new RestTemplateBuilder().basicAuthorization("Jabes Bonfim", "12345");
		}
	}
	
	@Before
	public void setUp(){
		/* ========== Setting up the scenario ========== */
		Student student = new Student(1L,"Jabes","jabes@email.com");
		BDDMockito.when(this.stRepository.findById(student.getId())).thenReturn(student);
	}
	
	
	/**
	 * List Student When Username And Password Are Incorrect Should Return Status Code 401
	 */
	@Test
	public void checkingIsIncorrectUserWhenListingStudents(){
		/* ========== Setting up the scenario ========== */
		restTemplate = restTemplate.withBasicAuth("1", "1");
		/* ========== Execution -> Request Student========== */
		 ResponseEntity<Student>  reponse = restTemplate.getForEntity("/v1/protected/students/", Student.class);
		 /* ========== Verifications========== */
		 Assertions.assertThat(reponse.getStatusCodeValue()).isEqualTo(401);
	}
	
	/**
	 * Get Student When Username And Password Are Incorrect Should Return Status Code 401
	 */
	@Test
	public void checkingCorrectUserWhenGerStudentById(){
		/* ========== Setting up the scenario ========== */
		restTemplate = restTemplate.withBasicAuth("1", "1");
		/* ========== Execution -> Request Student========== */
		 ResponseEntity<Student>  reponse = restTemplate.getForEntity("/v1/protected/students/1", Student.class);
		 /* ========== Verifications========== */
		 Assertions.assertThat(reponse.getStatusCodeValue()).isEqualTo(401);
	}
	
	/**
	 * Get Students When Username And Password Are Correct Should Return Status Code 200
	 */
	@Test
	public void checkingIsCorrectUserWhenListingStudents(){
		/* ========== Setting up the scenario ========== */
		List<Student> students = asList(new Student(1L,"Jabes","jabes@email.com"),new Student(2L,"Jabes 2","jabes2@email.com"));
		BDDMockito.when(this.stRepository.findAll()).thenReturn(students);

		/* ========== Execution -> Request Students========== */
		 ResponseEntity<Student>  response = restTemplate.getForEntity("/v1/protected/students/", Student.class);
		 
		 /* ========== Verifications========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	/**
	 * Get Student When Username And Password Are Correct Should Return Status Code 200
	 */
	@Test
	public void checkingCorrectUserWhenGerStudentByIdStatusCode200(){
		
		/* ========== Execution -> Request Student========== */
		 ResponseEntity<Student>  response = restTemplate.getForEntity("/v1/protected/students/{id}", Student.class,1L);
		 /* ========== Verifications========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	/**
	 * Get Student When Username And Password Are Correct And Student Does Not Exist Should Return Status Code 200
	 */
	@Test
	public void checkingCorrectUserWhenGerStudentByIdStatusCode404(){
		/* ========== Setting up the scenario ========== */
		long idUser = 2;
		/* ========== Execution -> Request Student========== */
		 ResponseEntity<Student>  response = restTemplate.getForEntity("/v1/protected/students/{id}", Student.class,idUser);
		 /* ========== Verifications========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	/**
	 * Delete Student When User role Admin should return status code 200
	 */
	@Test
	public void deleteStudentRoleAdminShouldReturnStatusCode200(){
		/* ========== Setting up the scenario ========== */
		BDDMockito.doNothing().when(this.stRepository).delete(1L);
		/* ========== Execution -> delete Student========== */
		 ResponseEntity<String>  response = restTemplate.exchange("/v1/admin/students/{id}", HttpMethod.DELETE,null,String.class,1L);
		 /* ========== Verifications========== */
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	
	/**
	 * Delete Student When User role Admin And Student Does Not Exist should return status code 404
	 */
	@Test
	public void deleteStudentRoleAdminAndDoesNotExistShouldReturnStatusCode404(){
		/* ========== Setting up the scenario ========== */
		BDDMockito.doNothing().when(this.stRepository).delete(1L);
		/* ========== Execution -> delete Student========== */
		 ResponseEntity<String>  response = restTemplate.exchange("/v1/admin/students/{id}", HttpMethod.DELETE,null,String.class,-1L);
		 /* ========== Verifications========== */
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	/**
	 * Delete Student When User role Admin And Student Does Not Exist should return status code 404
	 * @throws Exception 
	 */
	@Test
	@WithMockUser(username="test",password="tete",roles = {"USER","ADMIN"})
	public void deleteStudentRoleAdminAndDoesNotExistShouldReturnStatusCodeIsNotFound() throws Exception{
		/* ========== Setting up the scenario ========== */
		BDDMockito.doNothing().when(this.stRepository).delete(1L);
		/* ========== Execution And Verifications -> delete Student========== */
		mockmvc.perform(MockMvcRequestBuilders
				.delete("/v1/admin/students/{id}",-1L))
				.andExpect(MockMvcResultMatchers.status().isNotFound());
		
	}
	
	/**
	 * Delete Student When User Does Not have role Admin And Student Does Not Exist should return status code 403
	 * @throws Exception 
	 */
	@Test
	@WithMockUser(username="test",password="tete",roles = {"USER"})
	public void deleteStudentDoeNotHaveRoleAdminShouldReturnStatusCodeIsNotFound() throws Exception{
		/* ========== Setting up the scenario ========== */
		BDDMockito.doNothing().when(this.stRepository).delete(1L);
		/* ========== Execution And Verifications -> delete Student========== */
		mockmvc.perform(MockMvcRequestBuilders
				.delete("/v1/admin/students/{id}",-1L))
				.andExpect(MockMvcResultMatchers.status().isForbidden());
		
	}
	
	/**
	 * Verification create when Name is null should return status code 400
	 * @throws Exception 
	 */
	@Test
	public void createStudentRoleAdminShouldReturnStatusCode400() throws Exception{
		/* ========== Setting up the scenario ========== */
		Student student = new Student(3L,"","maria@email.com");
		BDDMockito.when(stRepository.save(student)).thenReturn(student);
		
		/* ========== Execution========== */
		 ResponseEntity<String>  response = restTemplate.postForEntity("/v1/admin/students/", student, String.class);
		 
		 /* ========== Verifications========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
		 //Assertions.assertThat(response.getBody()).contains("fieldMessage","O campo nome é obrigatório");
		
	}
	
	/**
	 * Verification create should return status code 201
	 * @throws Exception 
	 */
	@Test
	public void createStudentRoleAdminShouldReturnStatusCode200() throws Exception{
		/* ========== Setting up the scenario ========== */
		Student student = new Student(3L,"Maria","maria@email.com");
		BDDMockito.when(stRepository.save(student)).thenReturn(student);
		
		/* ========== Execution ========== */
		 ResponseEntity<Student>  response = restTemplate.postForEntity("/v1/admin/students/", student, Student.class);
		 
		 /* ========== Verifications========== */
		 Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
		 Assertions.assertThat(response.getBody().getId()).isNotNull();
		
	}
	
	
	
	
	
	
	
}

