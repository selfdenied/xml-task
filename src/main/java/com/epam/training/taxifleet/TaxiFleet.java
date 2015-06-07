package com.epam.training.taxifleet;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import com.epam.training.car.PassengerCar;

/* the container class for a list of taxi */
public class TaxiFleet {
	private List<PassengerCar> taxiFleetList = new ArrayList<PassengerCar>();

	/* getter and some delegate methods (to manage list operations) */
	public List<PassengerCar> getTaxiFleetList() {
		return Collections.unmodifiableList(taxiFleetList);
	}

	public boolean add(PassengerCar car) {
		return taxiFleetList.add(car);
	}

	public boolean remove(PassengerCar car) {
		return taxiFleetList.remove(car);
	}

	public void clear() {
		taxiFleetList.clear();
	}

	public boolean isEmpty() {
		return taxiFleetList.isEmpty();
	}
}
