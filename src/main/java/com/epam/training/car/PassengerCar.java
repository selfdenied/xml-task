package com.epam.training.car;

import com.epam.training.car.engine.ElectricEngine;
import com.epam.training.car.engine.InternalCombustionEngine;
import com.epam.training.car.feature.*;
import com.epam.training.constant.Constants;

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

	/* getters and setters */
	/* no need in validation cause we take data from XML/XSD */
	public int getCarID() {
		return carID;
	}

	public void setCarID(int carID) {
		this.carID = carID;
	}

	public int getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(int carPrice) {
		this.carPrice = carPrice;
	}

	public int getTopSpeed() {
		return topSpeed;
	}

	public void setTopSpeed(int topSpeed) {
		this.topSpeed = topSpeed;
	}

	public int getCurbWeight() {
		return curbWeight;
	}

	public void setCurbWeight(int curbWeight) {
		this.curbWeight = curbWeight;
	}

	public double getConsumption() {
		return consumption;
	}

	public void setConsumption(double consumption) {
		this.consumption = consumption;
	}

	public BodyStyle getBodyStyle() {
		return bodyStyle;
	}

	public void setBodyStyle(BodyStyle bodyStyle) {
		this.bodyStyle = bodyStyle;
	}

	public DriveArrangement getDriveArrangement() {
		return driveArrangement;
	}

	public void setDriveArrangement(DriveArrangement driveArrangement) {
		this.driveArrangement = driveArrangement;
	}

	public Gearbox getGearbox() {
		return gearbox;
	}

	public void setGearbox(Gearbox gearbox) {
		this.gearbox = gearbox;
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

	/* abstract methods to be implemented in the sub-classes */
	public abstract void setCombustionEngine(InternalCombustionEngine engine);

	public abstract void setElectricEngine(ElectricEngine engine);
}
