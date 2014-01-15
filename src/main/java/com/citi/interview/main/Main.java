package com.citi.interview.main;

import org.apache.log4j.Logger;

import com.citi.interview.runner.ProjectRunner;


/**
 * The main entrance for this project.
 * 
 * @author jzhang
 */
public class Main {

	private static final Logger logger = Logger.getLogger(Main.class);

    public static void main(String[] args) throws Exception {

		if (args.length == 0 || args.length > 1) {
			throw new Error(
					"Invalid number of arguments passed. Argument is directory to watch.");
        }

        logger.info("Starting File Watcher!");
        logger.info("Watching Directory = " + args[0]);

		ProjectRunner runner = new ProjectRunner(args[0]);
        runner.run();
        logger.info("=============== Started Scriptacular! ===============");
    }

}
