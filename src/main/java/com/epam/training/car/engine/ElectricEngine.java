package com.epam.training.car.engine;

import com.epam.training.car.feature.FuelType;
import com.epam.training.constant.Constants;
import com.epam.training.exception.IllegalSetValueException;

/* the class of an electric engine */
public class ElectricEngine {
	private int maxPower; // measured in PS
	private int maxTorque; // measured in N*m
	private FuelType fuelType; // type of fuel used

	public ElectricEngine() {
		this.fuelType = FuelType.ELECTRICITY;
	}

	/* getters and setters with validation */
	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) throws IllegalSetValueException {
		if (maxPower > 0) {
			this.maxPower = maxPower;
		} else {
			throw new IllegalSetValueException(
					"Engine max power should be positive");
		}
	}

	public int getMaxTorque() {
		return maxTorque;
	}

	public void setMaxTorque(int maxTorque) throws IllegalSetValueException {
		if (maxTorque > 0) {
			this.maxTorque = maxTorque;
		} else {
			throw new IllegalSetValueException(
					"Engine max torque should be positive");
		}
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	@Override
	public String toString() {
		return Constants.ELECTRIC_ENGINE_DESCRIPTION;
	}
}
