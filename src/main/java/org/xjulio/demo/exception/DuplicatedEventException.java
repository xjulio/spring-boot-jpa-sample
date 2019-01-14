/*
 * @(#)DuplicatedEventException.java
 *
 * Copyright 2019 Xjulio
 */
package org.xjulio.demo.exception;

/**
 * @author <a href="mailto:xjulio@gmail.com">Julio Cesar Damasceno</a>
 */
public class DuplicatedEventException extends RuntimeException {

	private static final long serialVersionUID = 4389785219295697082L;

	/**
	 * 
	 */
	public DuplicatedEventException() {
	}

	/**
	 * @param message
	 */
	public DuplicatedEventException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public DuplicatedEventException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public DuplicatedEventException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public DuplicatedEventException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
