package com.epam.training.parser.handler;

import org.apache.log4j.Logger;
import org.xml.sax.Attributes;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.training.car.*;
import com.epam.training.car.engine.*;
import com.epam.training.car.feature.BodyStyle;
import com.epam.training.car.feature.DriveArrangement;
import com.epam.training.car.feature.Gearbox;
import com.epam.training.constant.Constants;
import com.epam.training.exception.IllegalSetValueException;
import com.epam.training.parser.enumeration.CarEnum;
import com.epam.training.taxifleet.TaxiFleet;

public class CarHandler extends DefaultHandler {
	/* getting the logger reference */
	private static final Logger LOG = Logger.getLogger(CarHandler.class);
	private TaxiFleet taxiFleet;
	private PassengerCar car;
	private InternalCombustionEngine combustionEngine;
	private ElectricEngine electricEngine;
	private CarEnum currentEnum;

	public CarHandler() {
		this.taxiFleet = new TaxiFleet();
	}

	public TaxiFleet getTaxiFleet() {
		return taxiFleet;
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attrs) {
		try {
			switch (localName) {
			case Constants.COMBUSTION_CAR:
				car = new CombustionEnginePassengerCar();
				car.setCarID(Integer.parseInt(attrs.getValue(0).substring(1)));
				break;
			case Constants.ELECTRIC_CAR:
				car = new ElectricPassengerCar();
				car.setCarID(Integer.parseInt(attrs.getValue(0).substring(1)));
				break;
			case Constants.HYBRID_CAR:
				car = new HybridPassengerCar();
				car.setCarID(Integer.parseInt(attrs.getValue(0).substring(1)));
				break;
			case Constants.ENGINE:
				createProperEngine(attrs.getValue(0));
				break;
			default:
				if (!localName.equals("cars")) {
					currentEnum = CarEnum.valueOf(localName.toUpperCase());
				}
				break;
			}
		} catch (IllegalSetValueException exception) {
			LOG.error(exception.getMessage(), exception);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName) {
		try {
			switch (localName) {
			case Constants.COMBUSTION_CAR:
			case Constants.ELECTRIC_CAR:
			case Constants.HYBRID_CAR:
				taxiFleet.add(car);
				break;
			case Constants.ENGINE:
				addProperEngine(car);
				break;
			default:
				break;
			}
		} catch (IllegalSetValueException exception) {
			LOG.error(exception.getMessage(), exception);
		}
	}

	@Override
	public void characters(char[] ch, int start, int length) {
		String str = new String(ch, start, length).trim();
		try {
			if (currentEnum != null) {
				switch (currentEnum) {
				case PRICE:
					car.setCarPrice(Integer.parseInt(str));
					break;
				case SPEED:
					car.setTopSpeed(Integer.parseInt(str));
					break;
				case WEIGHT:
					car.setCurbWeight(Integer.parseInt(str));
					break;
				case CONSUMPTION:
					car.setConsumption(Double.parseDouble(str));
					break;
				case BODYSTYLE:
					car.setBodyStyle(BodyStyle.valueOf(str.toUpperCase()));
					break;
				case DRIVE:
					car.setDriveArrangement(DriveArrangement.valueOf(str));
					break;
				case GEARBOX:
					car.setGearbox(Gearbox.valueOf(str.toUpperCase()));
					break;
				case CYLINDERS:
					combustionEngine.setNumberOfCylinders(Integer.parseInt(str));
					break;
				case DISPLACEMENT:
					combustionEngine.setDisplacement(Integer.parseInt(str));
					break;
				case POWER:
					combustionEngine.setMaxPower(Integer.parseInt(str));
					break;
				case TORQUE:
					combustionEngine.setMaxTorque(Integer.parseInt(str));
					break;
				case TURBO:
					combustionEngine.setTurbocharged(Boolean.parseBoolean(str));
					break;
				case EPOWER:
					electricEngine.setMaxPower(Integer.parseInt(str));
					break;
				case ETORQUE:
					electricEngine.setMaxTorque(Integer.parseInt(str));
					break;
				default:
					throw new NoSuchFieldException(
							"Error. There is no such field in the PassengerCar object!");
				}
			}
			currentEnum = null;
		} catch (IllegalSetValueException | NoSuchFieldException exception) {
			LOG.error(exception.getMessage(), exception);
		}
	}

	private void createProperEngine(String engineType)
			throws IllegalSetValueException {
		switch (engineType) {
		case Constants.PETROL:
			combustionEngine = new PetrolEngine();
			break;
		case Constants.DIESEL:
			combustionEngine = new DieselEngine();
			break;
		case Constants.ELECTRICITY:
			electricEngine = new ElectricEngine();
			break;
		default:
			LOG.error("Error. Unknown type of engine!");
			break;
		}
	}

	private void addProperEngine(PassengerCar car)
			throws IllegalSetValueException {
		if (car instanceof CombustionEnginePassengerCar) {
			car.setCombustionEngine(combustionEngine);
		} else if (car instanceof ElectricPassengerCar) {
			car.setElectricEngine(electricEngine);
		} else {
			car.setCombustionEngine(combustionEngine);
			if (electricEngine != null) {
				car.setElectricEngine(electricEngine);
			}
		}
	}
}
