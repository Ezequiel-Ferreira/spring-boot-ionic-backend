package com.example.cursomc.resources.exception;

import java.io.Serializable;

public class StandardError implements Serializable {
	
	
	private static final long serialVersionUID = 1L;
	private Integer status;
	private String msg;
	private Long timerStamp;
	
	public StandardError(Integer status, String msg, Long timerStamp) {
		super();
		this.status = status;
		this.msg = msg;
		this.timerStamp = timerStamp;
	}
	
	
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public long getTimerStamp() {
		return timerStamp;
	}
	public void setTimerStamp(long timerStamp) {
		this.timerStamp = timerStamp;
	}

}
