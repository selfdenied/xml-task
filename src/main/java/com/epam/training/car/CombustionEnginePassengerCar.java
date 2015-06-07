package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;
import com.epam.training.exception.IllegalSetValueException;

/* the class of a passenger car with an internal combustion engine */
public class CombustionEnginePassengerCar extends PassengerCar {
	private InternalCombustionEngine engine;

	public CombustionEnginePassengerCar() {
		super(); // superclass constructor
	}

	public InternalCombustionEngine getEngine() {
		return engine;
	}

	@Override
	public void setCombustionEngine(InternalCombustionEngine engine)
			throws IllegalSetValueException {
		if (engine != null) {
			this.engine = engine;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper car engine object");
		}
	}

	@Override
	public void setElectricEngine(ElectricEngine engine) {
		return;
	}
}
