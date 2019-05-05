package br.com.jabes.apistudant.erro;


public class ErrorDetails {
	
	private String title;
	private int status;
	private String detail;
	private long timestamp;
	private String developerMessage;
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	public String getDetail() {
		return detail;
	}
	public void setDetail(String detail) {
		this.detail = detail;
	}
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getDeveloperMessage() {
		return developerMessage;
	}
	public void setDeveloperMessage(String developerMessage) {
		this.developerMessage = developerMessage;
	}
	
	public static final class Builder{
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		
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
		
		public ErrorDetails build(){
			ErrorDetails errodetails = new ErrorDetails();
			errodetails.developerMessage = this.developerMessage;
			errodetails.title = this.title;
			errodetails.detail = this.detail;
			errodetails.timestamp = this.timestamp;
			errodetails.status = this.status;
			
			return errodetails;
		}
	}
	
	

}
