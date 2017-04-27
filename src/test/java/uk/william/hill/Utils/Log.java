package uk.william.hill.Utils;

import org.apache.log4j.Logger;

public class Log {
	private static Logger Log = Logger.getLogger(Log.class);

	// This is to print log for the beginning of the test scenario, as we
	// usually run so many test scenarios as a test suite
	public static void startTestScenario(String scenarioName) {
		Log.info("**************************************************************************************");
		Log.info("$$$$$$$$$$$$$$$$$$$$$ Starting Scenario    " + scenarioName + "  $$$$$$$$$$$$$$$$$$$$$");
		Log.info("**************************************************************************************");
	}

	// This is to print log for the ending of the test case

	public static void endTestScenario(String scenarioName) {
		Log.info("XXXXXXXXXXXXXXXXXXXXXXX             " + "-END-" + "             XXXXXXXXXXXXXXXXXXXXXX");
	}

	// Need to create these methods, so that they can be called

	public static void info(String message) {
		Log.info(message);
	}

	public static void warn(String message) {

		// Log.warn(message);

	}

	public static void error(String message) {

		Log.error(message);

	}

	public static void fatal(String message) {

		Log.fatal(message);

	}

	public static void debug(String message) {

		Log.debug(message);

	}

}

