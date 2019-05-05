package br.com.jabes.apistudent.erro;

public class ResourceNotFoundDetails extends ErrorDetails{
	
	
	
	private ResourceNotFoundDetails() {
		// TODO Auto-generated constructor stub
	}
	
	public static final class ResouceNotFoundDetailsBuilder{
		private String title;
		private int status;
		private String detail;
		private long timestamp;
		private String developerMessage;
		
		public ResouceNotFoundDetailsBuilder() {
			// TODO Auto-generated constructor stub
		}
		
		public static ResouceNotFoundDetailsBuilder newBuilder(){
			return new ResouceNotFoundDetailsBuilder();
		}
		
		public ResouceNotFoundDetailsBuilder status(int status){
			this.status = status;
			return this;
		}
		
		public ResouceNotFoundDetailsBuilder title(String title){
			this.title = title;
			return this;
		}
		
		public ResouceNotFoundDetailsBuilder developerMessage(String developerMessage){
			this.developerMessage = developerMessage;
			return this;
		}
		
		public ResouceNotFoundDetailsBuilder detail(String detail){
			this.detail = detail;
			return this;
		}
		
		public ResouceNotFoundDetailsBuilder timestamp(long timestamp){
			this.timestamp = timestamp;
			return this;
		}
		
		public ResourceNotFoundDetails build(){
			ResourceNotFoundDetails resourceNotFoundDetails = new ResourceNotFoundDetails();
			resourceNotFoundDetails.setDeveloperMessage(this.developerMessage);
			resourceNotFoundDetails.setTitle(this.title);
			resourceNotFoundDetails.setDetail(this.detail);
			resourceNotFoundDetails.setTimestamp(this.timestamp);
			resourceNotFoundDetails.setStatus(this.status);
			
			return resourceNotFoundDetails;
		}
	}
	
	

	
	
}
