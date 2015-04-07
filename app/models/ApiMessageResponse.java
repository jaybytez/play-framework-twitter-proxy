package models;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "message")
public class ApiMessageResponse {
	
	public static ApiMessageResponse create(String devMsg, String userMsg, int errorCode,
            String errorCodeUrl) {
		ApiMessageResponse apiMessageResponse = new ApiMessageResponse(devMsg, userMsg, errorCode, errorCodeUrl);
		return apiMessageResponse;
	}
	private String devMsg;
	private int errorCode;
	private String errorCodeUrl;
	private String userMsg;
	public ApiMessageResponse() {
	    super();
	    // TODO Auto-generated constructor stub
    }
	public ApiMessageResponse(String devMsg, String userMsg, int errorCode,
            String errorCodeUrl) {
	    super();
	    this.devMsg = devMsg;
	    this.userMsg = userMsg;
	    this.errorCode = errorCode;
	    this.errorCodeUrl = errorCodeUrl;
    }
	public String getDevMsg() {
		return devMsg;
	}
	public int getErrorCode() {
		return errorCode;
	}
	public String getErrorCodeUrl() {
		return errorCodeUrl;
	}
	public String getUserMsg() {
		return userMsg;
	}
	public void setDevMsg(String devMsg) {
		this.devMsg = devMsg;
	}
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}
	public void setErrorCodeUrl(String errorCodeUrl) {
		this.errorCodeUrl = errorCodeUrl;
	}
	
	public void setUserMsg(String userMsg) {
		this.userMsg = userMsg;
	}

}
