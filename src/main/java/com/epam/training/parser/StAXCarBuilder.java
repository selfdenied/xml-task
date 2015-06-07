package com.epam.training.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

import org.apache.log4j.Logger;

import com.epam.training.car.*;
import com.epam.training.car.engine.*;
import com.epam.training.car.feature.*;
import com.epam.training.constant.Constants;
import com.epam.training.exception.IllegalSetValueException;
import com.epam.training.parser.enumeration.CarEnum;

public class StAXCarBuilder extends AbstractCarBuilder {
	/* getting the logger reference */
	private static final Logger LOG = Logger.getLogger(StAXCarBuilder.class);
	private XMLInputFactory inputFactory;
	private PassengerCar car;

	public StAXCarBuilder() {
		super();
		this.inputFactory = XMLInputFactory.newInstance();
	}

	@Override
	public void buildTaxiFleet(String filePath) {
		FileInputStream inputStream = null;
		XMLStreamReader reader = null;
		String elementName;

		try {
			inputStream = new FileInputStream(new File(filePath));
			reader = inputFactory.createXMLStreamReader(inputStream);
			// parsing the XML
			while (reader.hasNext()) {
				int type = reader.next();
				if (type == XMLStreamConstants.START_ELEMENT) {
					elementName = reader.getLocalName();
					switch (elementName) {
					case Constants.COMBUSTION_CAR:
						car = buildCombustionCar(reader);
						taxiFleet.add(car);
						break;
					case Constants.ELECTRIC_CAR:
						car = buildElectricCar(reader);
						taxiFleet.add(car);
						break;
					case Constants.HYBRID_CAR:
						car = buildHybridCar(reader);
						taxiFleet.add(car);
						break;
					default:
						break;
					}
				}
			}
		} catch (XMLStreamException exception) {
			LOG.error("StAX parser error!", exception);
		} catch (FileNotFoundException exception) {
			LOG.error("File '" + filePath + "' not found!", exception);
		} catch (IllegalSetValueException exception) {
			LOG.error(exception.getMessage(), exception);
		} finally {
			try {
				if (inputStream != null) {
					inputStream.close();
				}
			} catch (IOException exception) {
				LOG.error("Unable to close file!", exception);
			}
		}
	}

	private PassengerCar buildCombustionCar(XMLStreamReader reader)
			throws IllegalSetValueException, XMLStreamException {
		PassengerCar combustionCar = new CombustionEnginePassengerCar();
		combustionCar.setCarID(Integer.parseInt(reader.getAttributeValue(0)
				.substring(1)));
		initializeCar(combustionCar, reader);
		return combustionCar;
	}

	private PassengerCar buildElectricCar(XMLStreamReader reader)
			throws IllegalSetValueException, XMLStreamException {
		PassengerCar electricCar = new ElectricPassengerCar();
		electricCar.setCarID(Integer.parseInt(reader.getAttributeValue(0)
				.substring(1)));
		initializeCar(electricCar, reader);
		return electricCar;
	}

	private PassengerCar buildHybridCar(XMLStreamReader reader)
			throws IllegalSetValueException, XMLStreamException {
		PassengerCar hybridCar = new HybridPassengerCar();
		hybridCar.setCarID(Integer.parseInt(reader.getAttributeValue(0)
				.substring(1)));
		initializeCar(hybridCar, reader);
		return hybridCar;
	}

	private void initializeCar(PassengerCar car, XMLStreamReader reader)
			throws IllegalSetValueException, XMLStreamException {
		String elementName;
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = reader.getLocalName();
				switch (CarEnum.valueOf(elementName.toUpperCase())) {
				case PRICE:
					car.setCarPrice(Integer.parseInt(getXMLText(reader)));
					break;
				case SPEED:
					car.setTopSpeed(Integer.parseInt(getXMLText(reader)));
					break;
				case WEIGHT:
					car.setCurbWeight(Integer.parseInt(getXMLText(reader)));
					break;
				case CONSUMPTION:
					car.setConsumption(Double.parseDouble(getXMLText(reader)));
					break;
				case BODYSTYLE:
					car.setBodyStyle(BodyStyle.valueOf(getXMLText(reader)
							.toUpperCase()));
					break;
				case DRIVE:
					car.setDriveArrangement(DriveArrangement
							.valueOf(getXMLText(reader)));
					break;
				case GEARBOX:
					car.setGearbox(Gearbox.valueOf(getXMLText(reader)
							.toUpperCase()));
					break;
				case ENGINE:
					if (reader.getAttributeValue(0).equals(Constants.ELECTRICITY)) {
						car.setElectricEngine(initializeElectricEngine(reader));
					} else {
						car.setCombustionEngine(initializeCombustionEngine(reader));
					}
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = reader.getLocalName();
				if (elementName.equals(Constants.COMBUSTION_CAR)
						| elementName.equals(Constants.ELECTRIC_CAR)
						| elementName.equals(Constants.HYBRID_CAR)) {
					return;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in a 'car' tag!");
	}

	private InternalCombustionEngine initializeCombustionEngine(XMLStreamReader reader)
			throws IllegalSetValueException, XMLStreamException {
		InternalCombustionEngine engine; 
		String elementName;
		if (reader.getAttributeValue(0).equals(Constants.PETROL)) {
			engine = new PetrolEngine();
		} else {
			engine = new DieselEngine();
		}
		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = reader.getLocalName();
				switch (CarEnum.valueOf(elementName.toUpperCase())) {
				case CYLINDERS:
					engine.setNumberOfCylinders(Integer.parseInt(getXMLText(reader)));
					break;
				case DISPLACEMENT:
					engine.setDisplacement(Integer.parseInt(getXMLText(reader)));
					break;
				case POWER:
					engine.setMaxPower(Integer.parseInt(getXMLText(reader)));
					break;
				case TORQUE:
					engine.setMaxTorque(Integer.parseInt(getXMLText(reader)));
					break;
				case TURBO:
					engine.setTurbocharged(Boolean.parseBoolean(getXMLText(reader)));
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = reader.getLocalName();
				if (elementName.equals(Constants.ENGINE)) {
					return engine;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in an 'engine' tag!");
	}
	
	private ElectricEngine initializeElectricEngine(XMLStreamReader reader)
			throws IllegalSetValueException, XMLStreamException {
		ElectricEngine engine = new ElectricEngine(); 
		String elementName;

		while (reader.hasNext()) {
			int type = reader.next();
			switch (type) {
			case XMLStreamConstants.START_ELEMENT:
				elementName = reader.getLocalName();
				switch (CarEnum.valueOf(elementName.toUpperCase())) {
				case EPOWER:
					engine.setMaxPower(Integer.parseInt(getXMLText(reader)));
					break;
				case ETORQUE:
					engine.setMaxTorque(Integer.parseInt(getXMLText(reader)));
					break;
				default:
					break;
				}
				break;
			case XMLStreamConstants.END_ELEMENT:
				elementName = reader.getLocalName();
				if (elementName.equals(Constants.ENGINE)) {
					return engine;
				}
				break;
			}
		}
		throw new XMLStreamException("Unknown element in an 'engine' tag!");
	}

	private String getXMLText(XMLStreamReader reader) throws XMLStreamException {
		String text = null;
		if (reader.hasNext()) {
			reader.next();
			text = reader.getText();
		}
		return text;
	}
}
