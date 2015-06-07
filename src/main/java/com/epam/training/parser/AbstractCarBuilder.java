package com.epam.training.parser;

import com.epam.training.taxifleet.TaxiFleet;

public abstract class AbstractCarBuilder {
	protected TaxiFleet taxiFleet;

	public AbstractCarBuilder() {
		this.taxiFleet = new TaxiFleet();
	}
	
	public TaxiFleet getTaxiFleet() {
		return taxiFleet;
	}

	public abstract void buildTaxiFleet(String filePath);
}
