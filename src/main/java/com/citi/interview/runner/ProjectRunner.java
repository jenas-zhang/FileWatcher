package com.citi.interview.runner;

import java.io.File;

import org.apache.log4j.Logger;

import com.citi.interview.handler.impl.StatisticsFileAddHandler;
import com.citi.interview.handler.impl.StatisticsFileDelHandler;
import com.citi.interview.listener.StatisticsWatcher;

/**
 * Starter for the whole project. And you can also init spring configuration
 * here or add any other hooks.
 * 
 * @author jzhang
 */
public class ProjectRunner {

	private static final Logger logger = Logger.getLogger(ProjectRunner.class);

	private final String workingDirectory;
	private StatisticsFileAddHandler addHandler;
	private StatisticsFileDelHandler delHandler;
    private StatisticsWatcher statisticsWatcher;

	public ProjectRunner(String workingDirectory) {
		this.addHandler = new StatisticsFileAddHandler();
		this.delHandler = new StatisticsFileDelHandler();
        this.workingDirectory = workingDirectory;
    }

    public void run() throws Exception {
        startFileWatcher();
    }

    private void startFileWatcher() throws Exception {

        File workingDirectory = new File(this.workingDirectory);
        if (!workingDirectory.canRead() || !workingDirectory.isDirectory()) {
            throw new Error(this.workingDirectory + " MUST BE A DIRECTORY AND READABLE!");
        }

		this.statisticsWatcher = new StatisticsWatcher(this.addHandler,
				this.delHandler, workingDirectory, 5);
        addShutdownHook();

        statisticsWatcher.start();
		logger.info("=============== Successfully Started File Watcher ===============");
        logger.info("=============== Watch Directory :: " + this.workingDirectory);

    }

    private void addShutdownHook() {
		Runnable shutDownHook = new FileWatcherShutdownHook(
				this.statisticsWatcher);
        Runtime.getRuntime().addShutdownHook(new Thread(shutDownHook));
    }

    private static class FileWatcherShutdownHook implements Runnable {

		private final StatisticsWatcher statisticsWatcher;

		// TODO Check whether we have finished all the work through the handler.
		// Or we can prepare for some restore strategy
		public FileWatcherShutdownHook(StatisticsWatcher watcher) {
			this.statisticsWatcher = watcher;
        }

        public void run() {
			logger.info("Gracefully shutting down File Watcher. Hope you had a good Experience!");
            try {
				this.statisticsWatcher.stop();
            } catch (Exception e) {
				logger.error(
						"Caught exception while shutting down File Watcher.", e);
            }
        }
    }
}
