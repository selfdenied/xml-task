package com.epam.training.car.engine;

import com.epam.training.car.feature.FuelType;
import com.epam.training.constant.Constants;

/* the class of a petrol engine */
public class PetrolEngine extends InternalCombustionEngine {

	public PetrolEngine() {
		super(); // superclass constructor
		setFuelType(FuelType.PETROL);
	}

	@Override
	public String toString() {
		return Constants.PETROL_ENGINE_DESCRIPTION;
	}
}
