package br.com.jabes.apistudent.handler;

import java.util.Date;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.springframework.web.util.WebUtils;

import br.com.jabes.apistudent.erro.ErrorDetails;
import br.com.jabes.apistudent.erro.ResourceNotFoundDetails;
import br.com.jabes.apistudent.erro.ResourceNotFoundException;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

	

	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<?> handleResourceNotFoundExcepition(ResourceNotFoundException rfnException){
		
		ResourceNotFoundDetails rDetails = ResourceNotFoundDetails.ResouceNotFoundDetailsBuilder
		.newBuilder()
		.timestamp(new Date().getTime())
		.status(HttpStatus.NOT_FOUND.value())
		.title("Resource Not Found")
		.detail(rfnException.getMessage())
		.developerMessage(rfnException.getClass().getName())
		.build();
		
		return new ResponseEntity<>(rDetails,HttpStatus.NOT_FOUND);
		
	}
	
//	@ExceptionHandler(MethodArgumentNotValidException.class)
	
	
	protected ResponseEntity<Object> handleExceptionInternal(
			Exception ex, Object body, HttpHeaders headers, HttpStatus status, WebRequest request) {

		ErrorDetails rDetails = ErrorDetails.Builder
				.newBuilder()
				.timestamp(new Date().getTime())
				.status(status.value())
				.title("Internal Exception")
				.detail(ex.getMessage())
				.developerMessage(ex.getClass().getName())
				.build();
		return new ResponseEntity<Object>(rDetails, headers, status);
	}
	
	
	
}
