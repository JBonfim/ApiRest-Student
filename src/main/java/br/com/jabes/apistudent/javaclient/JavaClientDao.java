package br.com.jabes.apistudent.javaclient;

import java.util.List;

import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.client.RestTemplate;

import br.com.jabes.apistudent.model.PageableResponse;
import br.com.jabes.apistudent.model.Student;

public class JavaClientDao {
	
    private  RestTemplate restTemplate = new RestTemplateBuilder()
	.rootUri("http://localhost:8080/v1/protected/students").basicAuthorization("Jabes Bonfim", "12345").build();

    private RestTemplate restTemplateAdmin = new RestTemplateBuilder()
	.rootUri("http://localhost:8080/v1/admin/students").basicAuthorization("Jabes Bonfim", "12345").build();
    
    public Student findById(int id){
    	return restTemplate.getForEntity("/{id}", Student.class, id).getBody();
    }
    public List<Student> listAll(){
    	return restTemplate
				.exchange("/?sort=id,desc&sort=name,asc", HttpMethod.GET,null,new ParameterizedTypeReference<PageableResponse<Student>>() {
		}).getBody().getContent();
    }
    
    public Student save(Student student){
    	return  restTemplateAdmin
				.exchange("/", HttpMethod.POST,new HttpEntity<>(student,createJonHeader()),Student.class).getBody();
    }
    
    public void update(Student student){
    	 restTemplateAdmin.put("/", student);
    	
    }
    
    public void delete(Student student){
    	restTemplateAdmin
		.exchange("/", HttpMethod.DELETE,new HttpEntity<>(student,createJonHeader()),Student.class);
    }
    
    private  HttpHeaders createJonHeader(){
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_JSON);
		
		return headers;
		
	}
    

}
