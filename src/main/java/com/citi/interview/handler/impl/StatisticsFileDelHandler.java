package com.citi.interview.handler.impl;

import java.io.File;

import com.citi.interview.handler.AbstractBaseHandler;
import com.citi.interview.service.StatisticsManager;

/**
 * File delete handler for the StatisticsFileListener.
 * 
 * @author jzhang
 */
public class StatisticsFileDelHandler extends AbstractBaseHandler {

	public void doExecute(File fileToExecute) {
		StatisticsManager.getInstance().deleteFile(fileToExecute);
    }

}
