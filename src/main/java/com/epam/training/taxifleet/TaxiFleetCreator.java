package com.epam.training.taxifleet;

import com.epam.training.parser.AbstractCarBuilder;

/* 
 * the class for creation of taxiFleet using one of the builders (parsers)
 * available in the application 
 */
public class TaxiFleetCreator {

	/* method returns the TaxiFleet object created by a chosen builder (parser) */
	public static TaxiFleet constructTaxiFleet(AbstractCarBuilder builder,
			String filePath) {
		builder.buildTaxiFleet(filePath);
		return builder.getTaxiFleet();
	}
}
