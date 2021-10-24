package com.te.employeemanagementsystem.exceptions;

public class PasswordMismatchException extends Exception{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public PasswordMismatchException(String message) {
		super(message);
	}
}
