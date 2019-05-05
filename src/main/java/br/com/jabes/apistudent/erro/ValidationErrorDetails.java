package br.com.jabes.apistudent.erro;

import br.com.jabes.apistudent.erro.ResourceNotFoundDetails.ResouceNotFoundDetailsBuilder;


/**
 * @author Jabes
 *
 */
public class ValidationErrorDetails extends ErrorDetails {
	private String field;
	private String fieldMessage;
	
	public static final class Builder{
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		private String field;
		private String fieldMessage;
		
		public Builder() {
			// TODO Auto-generated constructor stub
		}
		
		public static Builder newBuilder(){
			return new Builder();
		}
		
		public Builder status(int status){
			this.status = status;
			return this;
		}
		
		public Builder title(String title){
			this.title = title;
			return this;
		}
		
		public Builder developerMessage(String developerMessage){
			this.developerMessage = developerMessage;
			return this;
		}
		
		public Builder detail(String detail){
			this.detail = detail;
			return this;
		}
		
		public Builder timestamp(long timestamp){
			this.timestamp = timestamp;
			return this;
		}
		
		public Builder field(String field){
			this.field = field;
			return this;
		}
		public Builder fieldMessage(String fieldMessage){
			this.fieldMessage = fieldMessage;
			return this;
		}
		
		public ValidationErrorDetails build(){
			ValidationErrorDetails resourceNotFoundDetails = new ValidationErrorDetails();
			resourceNotFoundDetails.setDeveloperMessage(this.developerMessage);
			resourceNotFoundDetails.setTitle(this.title);
			resourceNotFoundDetails.setDetail(this.detail);
			resourceNotFoundDetails.setTimestamp(this.timestamp);
			resourceNotFoundDetails.setStatus(this.status);
			resourceNotFoundDetails.field = this.field;
			resourceNotFoundDetails.fieldMessage = this.fieldMessage;
			return resourceNotFoundDetails;
		}
	}

	public String getField() {
		return field;
	}

	public String getFieldMessage() {
		return fieldMessage;
	}
	
	
	
}
