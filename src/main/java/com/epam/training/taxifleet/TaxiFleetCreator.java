package com.epam.training.taxifleet;

import com.epam.training.parser.AbstractCarBuilder;

public class TaxiFleetCreator {

	public static TaxiFleet constructTaxiFleet(AbstractCarBuilder builder, String filePath) {
		builder.buildTaxiFleet(filePath);
		return builder.getTaxiFleet();
	}
}
