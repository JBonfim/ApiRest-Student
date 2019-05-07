package br.com.jabes.apistudent.javaclient;

import java.util.Arrays;
import java.util.List;

import javax.rmi.CORBA.StubDelegate;

import org.springframework.boot.autoconfigure.security.SecurityProperties.Headers;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import br.com.jabes.apistudent.model.PageableResponse;
import br.com.jabes.apistudent.model.Student;

public class SpringClientTest {
	
	 
	public static void main(String[] args) {
		
		
		JavaClientDao dao = new JavaClientDao();
		
		//Request GET
//		System.out.println(dao.findById(1));
//		System.out.println(dao.listAll());
//		
		
		//POST
		
		Student studentPost = new Student();
		studentPost.setName("Kekel Rodrigues");
		studentPost.setEmail("teste@email.com");
		studentPost.setId(4L);
		
		//System.out.println(dao.save(studentPost));
		
		dao.delete(studentPost);
		
	}
	
	
}
