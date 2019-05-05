package br.com.jabes.apistudant.erro;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ResourceNotFoundException extends RuntimeException {
   public ResourceNotFoundException(String mensagem) {
	   super(mensagem);
	// TODO Auto-generated constructor stub
}
}
