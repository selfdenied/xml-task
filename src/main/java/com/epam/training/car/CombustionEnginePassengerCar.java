package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;

/* the class of a passenger car with an internal combustion engine */
public class CombustionEnginePassengerCar extends PassengerCar {
	private InternalCombustionEngine combustionEngine;

	public CombustionEnginePassengerCar() {
		super(); // superclass constructor
	}

	public InternalCombustionEngine getCombustionEngine() {
		return combustionEngine;
	}

	@Override
	public void setCombustionEngine(InternalCombustionEngine combustionEngine) {
		this.combustionEngine = combustionEngine;
	}

	/* this method is not applicable to this class */
	@Override
	public void setElectricEngine(ElectricEngine engine) {
		return;
	}
}
