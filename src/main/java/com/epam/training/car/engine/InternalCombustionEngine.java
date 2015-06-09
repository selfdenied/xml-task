package com.epam.training.car.engine;

import com.epam.training.car.feature.FuelType;

/* the class of some abstract internal combustion engine */
public abstract class InternalCombustionEngine {
	private int numberOfCylinders;
	private int displacement; // measured in cubic centimeters
	private int maxPower; // measured in PS
	private int maxTorque; // measured in N*m
	private boolean turbocharged; // naturally aspirated or not
	private FuelType fuelType; // type of fuel used

	InternalCombustionEngine() {
		super(); // superclass constructor (Object)
	}

	/* getters and setters */
	/* no need in validation cause we take data from XML/XSD */
	public int getNumberOfCylinders() {
		return numberOfCylinders;
	}

	public void setNumberOfCylinders(int numberOfCylinders) {
		this.numberOfCylinders = numberOfCylinders;
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement) {
		this.displacement = displacement;
	}

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

	public boolean isTurbocharged() {
		return turbocharged;
	}

	public void setTurbocharged(boolean turbocharged) {
		this.turbocharged = turbocharged;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) {
		this.fuelType = fuelType;
	}
}
