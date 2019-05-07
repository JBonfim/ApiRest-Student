package br.com.jabes.apistudent.javaclient;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringBufferInputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.tomcat.util.codec.binary.Base64;
import org.apache.tomcat.util.http.fileupload.IOUtils;

public class JavaClientTest {
	public static void main(String[] args) {
		HttpURLConnection connection =null;
		BufferedReader reader = null;
		try {
			URL url  = new URL("http://localhost:8080/v1/protected/students");
			connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.addRequestProperty("Authorization", "Basic " + encodeUsernamePassword("Jabes Bonfim","12345"));
			System.out.println(encodeUsernamePassword("Jabes Bonfim","12345"));
			reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));

			StringBuilder JsonSB = new StringBuilder();
			String line;
			while((line = reader.readLine()) != null){
				JsonSB.append(line);
			}
			System.out.println(JsonSB.toString());
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(reader);
			if(connection != null)
				connection.disconnect();
		}
	}
	
	public static String encodeUsernamePassword(String user,String password){
		String userNamePassword = user + ":" + password;
		
		return new String(Base64.encodeBase64(userNamePassword.getBytes()));
	}
}
