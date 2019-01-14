/*
 * @(#)ActorNotFoundException.java
 *
 * Copyright 2019 Xjulio
 */
package org.xjulio.demo.exception;

/**
 * @author <a href="mailto:xjulio@gmail.com">Julio Cesar Damasceno</a>
 */
public class ActorNotFoundException extends RuntimeException {

	private static final long serialVersionUID = -3586903850258827566L;

	/**
	 * 
	 */
	public ActorNotFoundException() {
	}

	/**
	 * @param message
	 */
	public ActorNotFoundException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public ActorNotFoundException(Throwable cause) {
		super(cause);
	}

	/**
	 * @param message
	 * @param cause
	 */
	public ActorNotFoundException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 * @param cause
	 * @param enableSuppression
	 * @param writableStackTrace
	 */
	public ActorNotFoundException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
