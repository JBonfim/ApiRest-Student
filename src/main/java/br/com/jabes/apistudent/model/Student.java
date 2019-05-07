package br.com.jabes.apistudent.model;

import javax.persistence.Entity;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author Jabes
 *
 */
@Entity
public class Student extends AbstractEntity {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@NotEmpty(message="O campo nome é obrigatório")
	private String name;
	
	@NotEmpty(message="o campo email é obrigatório")
	@Email(message="Email inválido.")
	private String email;
	
	
	
	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Student() {
		// TODO Auto-generated constructor stub
	}
	
	public Student(String name, String email) {
		// TODO Auto-generated constructor stub
		this.name = name;
		this.email = email;
	}
	
	

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return "[Name: "+name+", email: "+email+"]";
	}
	
	
}

