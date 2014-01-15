package com.citi.interview.handler;

import java.io.File;

import org.apache.log4j.Logger;

/**
 * Base abstract class for all handlers. To be a template for fault tolerance
 * and any hooks.
 * 
 * @author jzhang
 */
public abstract class AbstractBaseHandler {

	private static final Logger logger = Logger
			.getLogger(AbstractBaseHandler.class);

	public void execute(File fileToExecute) {
		try {
			doExecute(fileToExecute);
		} catch (RuntimeException e) {
			// TODO Process the exception.
			logger.error("Handler execution errors.", e);
		}
	}

	/**
	 * Abstract method to be implemented in the subclass.
	 * 
	 * @param fileToExecute
	 */
	protected abstract void doExecute(File fileToExecute);
}
