package br.com.os.controller.exceptions;

import java.io.Serializable;

public class StandartError implements Serializable {

	private static final long serialVersionUID = 1L;

	private Long timeStamp;
	private Integer status;
	private String error;

	public StandartError() {
		super();
	}

	public StandartError(Long timeStamp, Integer status, String error) {
		super();
		this.timeStamp = timeStamp;
		this.status = status;
		this.error = error;
	}

	public Long getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Long timeStamp) {
		this.timeStamp = timeStamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getError() {
		return error;
	}

	public void setError(String error) {
		this.error = error;
	}

}
