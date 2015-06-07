package com.epam.training.run;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.apache.log4j.xml.DOMConfigurator;

import com.epam.training.constant.Constants;
import com.epam.training.exception.LogicInvalidInitializationException;
import com.epam.training.factory.CarBuilderFactory;
import com.epam.training.logic.TaxiFleetLogicOperations;
import com.epam.training.parser.AbstractCarBuilder;
import com.epam.training.taxifleet.TaxiFleet;
import com.epam.training.taxifleet.TaxiFleetCreator;
import com.epam.training.util.PrintReportManager;

public class Runner {
	/* getting the logger reference */
	final static Logger LOG = Logger.getLogger(Runner.class);

	/* initializing the logger configuration */
	static {
		new DOMConfigurator().doConfigure(Constants.LOGGER_CONFIG_FILE_PATH,
				LogManager.getLoggerRepository());
	}

	public static void main(String[] args) {
		/* needed to print a report */
		PrintReportManager printReportManager = new PrintReportManager();
		/* initializing a building parser */
		// use DOM, SAX or STAX values
		AbstractCarBuilder builder = CarBuilderFactory.createCarBuilder("DOM");
		/* generates a taxi fleet */
		TaxiFleet taxiFleet = TaxiFleetCreator.constructTaxiFleet(
				builder, Constants.INPUT_FILE_PATH);

		try {
			// initializing logic operations
			TaxiFleetLogicOperations logic = new TaxiFleetLogicOperations(taxiFleet);
			// printing a report into a file
			printReportManager.printReport(logic);
		} catch (LogicInvalidInitializationException exception) {
			LOG.error("TaxiFleetLogic constructor does not accept 'null' values", exception);
			LOG.warn("The process of printing a report into a file has been aborted");
		}
	}
}
