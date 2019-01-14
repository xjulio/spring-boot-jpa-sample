/*
 * @(#)InvalidFieldUpdateException.java
 *
 * Copyright 2019 Xjulio
 */
package org.xjulio.demo.exception;

/**
 * @author <a href="mailto:xjulio@gmail.com">Julio Cesar Damasceno</a>
 */
public class InvalidFieldUpdateException extends RuntimeException {

	private static final long serialVersionUID = -2042059713526881109L;

	/**
	 * 
	 */
	public InvalidFieldUpdateException() {
	}

	/**
	 * @param message
	 */
	public InvalidFieldUpdateException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public InvalidFieldUpdateException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public InvalidFieldUpdateException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public InvalidFieldUpdateException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
