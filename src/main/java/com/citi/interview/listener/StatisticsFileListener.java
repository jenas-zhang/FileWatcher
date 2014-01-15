package com.citi.interview.listener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import com.citi.interview.handler.impl.StatisticsFileAddHandler;
import com.citi.interview.handler.impl.StatisticsFileDelHandler;

/**
 * File Listener for apache io.
 * 
 * @author jzhang
 */
public class StatisticsFileListener implements FileAlterationListener {

    private static final Logger logger = Logger.getLogger(StatisticsFileListener.class);

	private final StatisticsFileAddHandler fileAddHandler;
	private final StatisticsFileDelHandler fileDelHandler;

	public StatisticsFileListener(StatisticsFileAddHandler addHandler,
			StatisticsFileDelHandler delHandler) {
		this.fileAddHandler = addHandler;
		this.fileDelHandler = delHandler;
    }

    public void onStart(FileAlterationObserver fileAlterationObserver) {
		logger.info("Listener is starting up...");
    }

    public void onDirectoryCreate(File file) {
        // do nothing
    }

    public void onDirectoryChange(File file) {
        // do nothing
    }

    public void onDirectoryDelete(File file) {
        // do nothing
    }

    /**
     * Method called when a file is created in the directory watched.
     *
     * @param file the file that is created.
     */
    public void onFileCreate(File file) {
		fileAddHandler.execute(file);
    }

    public void onFileChange(File file) {
		fileAddHandler.execute(file);
    }

    public void onFileDelete(File file) {
		fileDelHandler.execute(file);
    }

    public void onStop(FileAlterationObserver fileAlterationObserver) {
        // do nothing
    }

}
