package com.citi.interview.handler.impl;

import java.io.File;

import com.citi.interview.handler.AbstractBaseHandler;
import com.citi.interview.service.StatisticsManager;

/**
 * File add handler for the StatisticsFileListener.
 * 
 * @author jzhang
 */
public class StatisticsFileAddHandler extends AbstractBaseHandler {

	protected void doExecute(File fileToExecute) {
		StatisticsManager.getInstance().addFile(fileToExecute);
    }

}
