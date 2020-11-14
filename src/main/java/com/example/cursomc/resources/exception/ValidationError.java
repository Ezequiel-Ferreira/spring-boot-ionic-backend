package com.example.cursomc.resources.exception;

import java.util.ArrayList;
import java.util.List;

public class ValidationError extends StandardError{
	private static final long serialVersionUID = 1L;
	
	private List<FieldMessage> list = new ArrayList<>();
	
	public ValidationError(Integer status, String msg, Long timerStamp) {
		super(status, msg, timerStamp);
		
	}

	public List<FieldMessage> getList() {
		return list;
	}

	public void addMessage(String fildName, String message) {
		this.list.add(new FieldMessage(fildName, message));
		
	}

	

}
