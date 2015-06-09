package com.epam.training.parser;

import com.epam.training.taxifleet.TaxiFleet;

/* the class of some abstract builder */
public abstract class AbstractCarBuilder {
	/* the list of taxi cars (will be used by all sub-classes) */
	protected TaxiFleet taxiFleet;

	public AbstractCarBuilder() {
		this.taxiFleet = new TaxiFleet();
	}

	/* a standard getter */
	public TaxiFleet getTaxiFleet() {
		return taxiFleet;
	}

	/* this method will be implemented by all sub-classes in a different manner */
	public abstract void buildTaxiFleet(String filePath);
}
