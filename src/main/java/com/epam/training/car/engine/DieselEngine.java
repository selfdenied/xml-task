package com.epam.training.car.engine;

import com.epam.training.car.feature.FuelType;
import com.epam.training.constant.Constants;

/* the class of a diesel engine */
public class DieselEngine extends InternalCombustionEngine {

	public DieselEngine() {
		super(); // superclass constructor
		setFuelType(FuelType.DIESEL);
	}

	@Override
	public String toString() {
		return Constants.DIESEL_ENGINE_DESCRIPTION;
	}
}
