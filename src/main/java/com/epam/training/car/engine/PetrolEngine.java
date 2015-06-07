package com.epam.training.car.engine;

import com.epam.training.car.feature.FuelType;
import com.epam.training.constant.Constants;
import com.epam.training.exception.IllegalSetValueException;

/* the class of a petrol engine */
public class PetrolEngine extends InternalCombustionEngine {

	public PetrolEngine() throws IllegalSetValueException {
		super(); // superclass constructor
		setFuelType(FuelType.PETROL);
	}

	@Override
	public String toString() {
		return Constants.PETROL_ENGINE_DESCRIPTION;
	}
}
