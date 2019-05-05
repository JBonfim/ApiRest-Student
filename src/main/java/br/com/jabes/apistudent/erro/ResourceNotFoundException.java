package br.com.jabes.apistudent.erro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String mensagem) {
	   super(mensagem);
	// TODO Auto-generated constructor stub
}
}
