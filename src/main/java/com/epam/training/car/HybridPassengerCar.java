package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;

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
	public void setCombustionEngine(InternalCombustionEngine mainEngine) {
		this.mainEngine = mainEngine;
	}

	public ElectricEngine getAdditionalEngine() {
		return additionalEngine;
	}

	@Override
	public void setElectricEngine(ElectricEngine additionalEngine) {
		this.additionalEngine = additionalEngine;
	}
}
