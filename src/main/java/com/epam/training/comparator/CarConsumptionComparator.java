package com.epam.training.comparator;

import java.util.Comparator;

import com.epam.training.car.PassengerCar;

/* the comparator that sorts a list of cars according to their consumption */
public class CarConsumptionComparator implements Comparator<PassengerCar> {

	@Override
	public int compare(PassengerCar car1, PassengerCar car2) {
		return new Double(car1.getConsumption()).compareTo(new Double(car2.getConsumption()));
	}
}
