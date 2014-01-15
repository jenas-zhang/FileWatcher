package com.citi.interview.listener;

import java.io.File;

import org.apache.commons.io.monitor.FileAlterationListener;
import org.apache.commons.io.monitor.FileAlterationMonitor;
import org.apache.commons.io.monitor.FileAlterationObserver;
import org.apache.log4j.Logger;

import com.citi.interview.handler.impl.StatisticsFileAddHandler;
import com.citi.interview.handler.impl.StatisticsFileDelHandler;

/**
 * File Watcher server stater.
 * 
 * @author jzhang
 */
public class StatisticsWatcher {

	private static final Logger logger = Logger.getLogger(StatisticsWatcher.class);

    private final File watchDirectory;
	private final StatisticsFileAddHandler addHandler;
	private final StatisticsFileDelHandler delHandler;
    private final FileAlterationMonitor directoryMonitor;

	public StatisticsWatcher(StatisticsFileAddHandler addHandler,
			StatisticsFileDelHandler delHandler, File scriptsDirectory,
			long interval) {
        this.watchDirectory = scriptsDirectory;
		this.addHandler = addHandler;
		this.delHandler = delHandler;
        directoryMonitor = new FileAlterationMonitor(interval * 1000);
    }

    public void start() throws Exception {
        FileAlterationObserver observer = new FileAlterationObserver(watchDirectory);
		FileAlterationListener listener = new StatisticsFileListener(
				this.addHandler, this.delHandler);
        observer.addListener(listener);
        this.directoryMonitor.addObserver(observer);
        this.directoryMonitor.start();

        logger.debug("Started to Watch Directory " + this.watchDirectory + " at intervals " + this.directoryMonitor.getInterval());
    }

    public void stop() throws Exception {
        this.directoryMonitor.stop();
        logger.debug("Stopping to Monitor Directory " + this.watchDirectory);
    }

}
