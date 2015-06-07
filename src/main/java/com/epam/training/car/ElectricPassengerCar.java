package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;
import com.epam.training.exception.IllegalSetValueException;

/* the class of an electric passenger car */
public class ElectricPassengerCar extends PassengerCar {
	private ElectricEngine engine;

	public ElectricPassengerCar() {
		super(); // superclass constructor
	}

	public ElectricEngine getEngine() {
		return engine;
	}

	@Override
	public void setCombustionEngine(InternalCombustionEngine engine) {
		return;
	}

	@Override
	public void setElectricEngine(ElectricEngine engine)
			throws IllegalSetValueException {
		if (engine != null) {
			this.engine = engine;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper car engine object");
		}
	}
}
