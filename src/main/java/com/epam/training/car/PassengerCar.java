package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;
import com.epam.training.car.feature.*;
import com.epam.training.constant.Constants;
import com.epam.training.exception.IllegalSetValueException;

/* the class of some abstract passenger car */
public abstract class PassengerCar {
	private int carID;
	private int carPrice; // measured in US Dollars
	private int topSpeed; // measured in km/hour
	private int curbWeight; // measured in kg
	private double consumption; // measured in l/100km (or eq. for el. cars)
	private BodyStyle bodyStyle;
	private DriveArrangement driveArrangement;
	private Gearbox gearbox;

	PassengerCar() {
		super(); // superclass constructor (Object)
	}

	/* getters and setters with validation */
	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) throws IllegalSetValueException {
		if (carID > 0) {
			this.carID = carID;
		} else {
			throw new IllegalSetValueException("Car ID should be positive");
		}
	}

	public int getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(int carPrice) throws IllegalSetValueException {
		if (carPrice > 0) {
			this.carPrice = carPrice;
		} else {
			throw new IllegalSetValueException("Car price should be positive");
		}
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) throws IllegalSetValueException {
		if (topSpeed >= Constants.MIN_TOP_SPEED
				&& topSpeed <= Constants.MAX_TOP_SPEED) {
			this.topSpeed = topSpeed;
		} else {
			throw new IllegalSetValueException(
					"Car's top speed should be between 50 and 450 km/hour");
		}
	}

	public int getCurbWeight() {
		return curbWeight;
	}

	public void setCurbWeight(int curbWeight) throws IllegalSetValueException {
		if (curbWeight > 0) {
			this.curbWeight = curbWeight;
		} else {
			throw new IllegalSetValueException(
					"Car's curb weight should be positive");
		}
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption)
			throws IllegalSetValueException {
		if (consumption > 0) {
			this.consumption = consumption;
		} else {
			throw new IllegalSetValueException(
					"Car's consumption should be positive");
		}
	}

	public BodyStyle getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(BodyStyle bodyStyle)
			throws IllegalSetValueException {
		if (bodyStyle != null) {
			this.bodyStyle = bodyStyle;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper body style value");
		}
	}

	public DriveArrangement getDriveArrangement() {
		return driveArrangement;
	}

	public void setDriveArrangement(DriveArrangement driveArrangement)
			throws IllegalSetValueException {
		if (driveArrangement != null) {
			this.driveArrangement = driveArrangement;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper drive arrangement");
		}
	}

	public Gearbox getGearbox() {
		return gearbox;
	}

	public void setGearbox(Gearbox gearbox) throws IllegalSetValueException {
		if (gearbox != null) {
			this.gearbox = gearbox;
		} else {
			throw new IllegalSetValueException(
					"Cannot accept a 'null' value. Enter a proper gearbox object");
		}
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append(Constants.CAR_ID_MESSAGE + "c" + carID);
		builder.append(Constants.CAR_PRICE_MESSAGE + carPrice + Constants.USD);
		builder.append(Constants.CAR_TOP_SPEED_MESSAGE + topSpeed
				+ Constants.KM_PER_HOUR);
		builder.append(Constants.CAR_CURB_WEIGHT_MESSAGE + curbWeight
				+ Constants.KG);
		builder.append(Constants.CAR_CONSUMPTION_MESSAGE + consumption
				+ Constants.LITERS_PER_KM);
		builder.append(Constants.CAR_BODYSTYLE_MESSAGE + bodyStyle);
		builder.append(Constants.CAR_DRIVE_MESSAGE + driveArrangement);
		builder.append(Constants.CAR_GEARBOX_MESSAGE + gearbox);
		builder.append(Constants.NEW_LINE_SYMBOL);

		return builder.toString();
	}

	public abstract void setCombustionEngine(InternalCombustionEngine engine)
			throws IllegalSetValueException;

	public abstract void setElectricEngine(ElectricEngine engine)
			throws IllegalSetValueException;
}
