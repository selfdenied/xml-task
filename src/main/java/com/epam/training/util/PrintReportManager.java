package com.epam.training.util;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import org.apache.log4j.Logger;

import com.epam.training.car.PassengerCar;
import com.epam.training.constant.Constants;
import com.epam.training.logic.TaxiFleetLogicOperations;

/* the class is responsible for printing reports on the basis of app's business logic */
public class PrintReportManager {
	/* getting the logger reference */
	final static Logger LOG = Logger.getLogger(PrintReportManager.class);

	public void printReport(TaxiFleetLogicOperations logic) {
		PrintWriter writer = null;
		// trying to open a file and write a report in it
		try {
			if (!logic.getTaxiFleetList().isEmpty()) {
				writer = new PrintWriter(Constants.OUTPUT_FILE_PATH,
						Constants.ENCODING);
				LOG.info("Writing into a file...");
				writer.println(Constants.TOTAL_PRICE_MESSAGE
						+ logic.calculateTaxiFleetTotalPrice() + Constants.USD);
				writer.println(Constants.TAXI_LIST_MESSAGE);
				writer.println(TaxiListShortDescription(logic
						.taxiFleetSortedAccordingToConsumption()));
				writer.println(Constants.CAR_SPEED_RANGE_MESSAGE);
				writer.println(TaxiListShortDescription(logic
						.carsOfTheSpecificMaxSpeedRange(180, 220)));
				LOG.info("End of process...");
			} else {
				LOG.warn("Warning. Taxi fleet list is empty! Report printing is aborted");
			}
		} catch (IOException exception) {
			LOG.error("Error. Can't write into file!", exception);
		} finally {
			if (writer != null) {
				writer.close(); // we close the stream
			}
		}
	}

	/* the method gives a short description of each car (taxi) in the list */
	private String TaxiListShortDescription(List<PassengerCar> taxiList) {
		StringBuilder builder = new StringBuilder();
		for (PassengerCar car : taxiList) {
			builder.append(car.toString());
		}
		return builder.toString();
	}
}
