package com.sk.kafkademo.utill;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ModelApiResponse {

	private String status;
	
	private String message;
	
	private String body;

	public ModelApiResponse(String status, String message, String body) {
		this.status = status;
		this.message = message;
		this.body = body;
	}

	@Override
	public String toString() {
		return "{\r\n" + 
				"  \"status\":\""+this.status+"\",\r\n" + 
				"  \"message\":\""+this.message+"\",\r\n" + 
				"  \"body\":\"" +this.body+ "\"\r\n" + 
				"}";
	}
	
	
	
}
