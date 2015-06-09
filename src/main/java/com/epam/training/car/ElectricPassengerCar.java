package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;

/* the class of an electric passenger car */
public class ElectricPassengerCar extends PassengerCar {
	private ElectricEngine electricEngine;

	public ElectricPassengerCar() {
		super(); // superclass constructor
	}

	public ElectricEngine getElectricEngine() {
		return electricEngine;
	}

	/* this method is not applicable to this class */
	@Override
	public void setCombustionEngine(InternalCombustionEngine engine) {
		return;
	}

	@Override
	public void setElectricEngine(ElectricEngine electricEngine) {
		this.electricEngine = electricEngine;
	}
}
