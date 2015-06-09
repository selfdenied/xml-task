package com.epam.training.car.engine;

import com.epam.training.car.feature.FuelType;
import com.epam.training.constant.Constants;

/* the class of an electric engine */
public class ElectricEngine {
	private int maxPower; // measured in PS
	private int maxTorque; // measured in N*m
	private FuelType fuelType; // type of fuel used

	public ElectricEngine() {
		this.fuelType = FuelType.ELECTRICITY;
	}

	/* getters and setters */
	/* no need in validation cause we take data from XML/XSD */
	public int getMaxPower() {
		return maxPower;
	}

	public void setMaxPower(int maxPower) {
		this.maxPower = maxPower;
	}

	public int getMaxTorque() {
		return maxTorque;
	}

	public void setMaxTorque(int maxTorque) {
		this.maxTorque = maxTorque;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}

	@Override
	public String toString() {
		return Constants.ELECTRIC_ENGINE_DESCRIPTION;
	}
}
