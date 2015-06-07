package com.epam.training.car.engine;

import com.epam.training.car.feature.FuelType;
import com.epam.training.exception.IllegalSetValueException;
import com.epam.training.constant.Constants;

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

	/* getters and setters with validation */
	public int getNumberOfCylinders() {
		return numberOfCylinders;
	}

	public void setNumberOfCylinders(int numberOfCylinders)
			throws IllegalSetValueException {
		if (numberOfCylinders >= Constants.MIN_NUMBER_OF_CYLINDERS
				&& numberOfCylinders <= Constants.MAX_NUMBER_OF_CYLINDERS) {
			this.numberOfCylinders = numberOfCylinders;
		} else {
			throw new IllegalSetValueException(
					"Number of cylinders should be between 2 and 16");
		}
	}

	public int getDisplacement() {
		return displacement;
	}

	public void setDisplacement(int displacement)
			throws IllegalSetValueException {
		if (displacement > 0) {
			this.displacement = displacement;
		} else {
			throw new IllegalSetValueException(
					"Engine displacement should be positive");
		}
	}

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

	public boolean isTurbocharged() {
		return turbocharged;
	}

	public void setTurbocharged(boolean turbocharged) {
		this.turbocharged = turbocharged;
	}

	public FuelType getFuelType() {
		return fuelType;
	}

	public void setFuelType(FuelType fuelType) throws IllegalSetValueException {
		if (fuelType != null) {
			this.fuelType = fuelType;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper fuel type object");
		}
	}
}
