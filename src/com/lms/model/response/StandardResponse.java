package com.lms.model.response;

public class StandardResponse {
	private int status;
	private String message;
	
	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
	public StandardResponse() {
		
	}

	@Override
	public String toString() {
		return "StandardResponse [status=" + status + ", message=" + message + "]";
	}

	public StandardResponse(int status, String message) {
		this.status = status;
		this.message = message;
	}
	
	public String toJson() {
		String json="{";
				json+="\"status\":\""+status+"\",";
				json+="\"message\":\""+message+"\",";
				json+="}";
		return json;		
	}
	
}
