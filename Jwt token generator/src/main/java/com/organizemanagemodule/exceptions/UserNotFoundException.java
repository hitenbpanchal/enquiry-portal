package com.organizemanagemodule.exceptions;

public class UserNotFoundException extends RuntimeException {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	String fieldName;
	Integer fieldValue;
	public UserNotFoundException(String fieldName, Integer fieldValue) {
		super(String.format("User not found with %s : %d", fieldName,fieldValue ));
		this.fieldName = fieldName;
		this.fieldValue = fieldValue;
	}
	
	
}
