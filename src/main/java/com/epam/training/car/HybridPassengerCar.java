package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;
import com.epam.training.exception.IllegalSetValueException;

/* the class of a hybrid drive passenger car */
public class HybridPassengerCar extends PassengerCar {
	private InternalCombustionEngine mainEngine;
	private ElectricEngine additionalEngine;

	public HybridPassengerCar() {
		super(); // superclass constructor
	}

	public InternalCombustionEngine getMainEngine() {
		return mainEngine;
	}

	@Override
	public void setCombustionEngine(InternalCombustionEngine mainEngine)
			throws IllegalSetValueException {
		if (mainEngine != null) {
			this.mainEngine = mainEngine;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper car engine object");
		}
	}

	public ElectricEngine getAdditionalEngine() {
		return additionalEngine;
	}

	@Override
	public void setElectricEngine(ElectricEngine additionalEngine)
			throws IllegalSetValueException {
		if (additionalEngine != null) {
			this.additionalEngine = additionalEngine;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper car engine object");
		}
	}
}
