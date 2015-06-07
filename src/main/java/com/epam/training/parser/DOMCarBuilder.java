package com.epam.training.parser;

import java.io.IOException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.training.car.*;
import com.epam.training.car.engine.*;
import com.epam.training.car.feature.*;
import com.epam.training.constant.Constants;
import com.epam.training.exception.IllegalSetValueException;
import com.epam.training.parser.enumeration.CarEnum;

public class DOMCarBuilder extends AbstractCarBuilder {
	/* getting the logger reference */
	private static final Logger LOG = Logger.getLogger(DOMCarBuilder.class);
	private DocumentBuilder documentBuilder;

	public DOMCarBuilder() {
		super();
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		try {
			this.documentBuilder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException exception) {
			LOG.error("Error. Parser configuration problem!", exception);
		}
	}

	@Override
	public void buildTaxiFleet(String filePath) {
		Document document;
		try {
			document = documentBuilder.parse(filePath);
			Element root = document.getDocumentElement();
			NodeList combustionCarsList = root.getElementsByTagName(Constants.COMBUSTION_CAR);
			NodeList electricCarsList = root.getElementsByTagName(Constants.ELECTRIC_CAR);
			NodeList hybridCarsList = root.getElementsByTagName(Constants.HYBRID_CAR);

			for (int i = 0; i < combustionCarsList.getLength(); i++) {
				Element combustionCarElement = (Element) combustionCarsList.item(i);
				PassengerCar combustionCar = buildCombustionCar(combustionCarElement);
				taxiFleet.add(combustionCar);
			}
			for (int i = 0; i < electricCarsList.getLength(); i++) {
				Element electricCarElement = (Element) electricCarsList.item(i);
				PassengerCar electricCar = buildElectricCar(electricCarElement);
				taxiFleet.add(electricCar);
			}
			for (int i = 0; i < hybridCarsList.getLength(); i++) {
				Element hybridCarElement = (Element) hybridCarsList.item(i);
				PassengerCar hybridCar = buildHybridCar(hybridCarElement);
				taxiFleet.add(hybridCar);
			}
		} catch (SAXException exception) {
			LOG.error("Error. Document parsing failure!", exception);
		} catch (IOException exception) {
			LOG.error("Error. I/O failure!", exception);
		}
	}

	private PassengerCar buildCombustionCar(Element combustionCarElement) {
		PassengerCar combustionCar = new CombustionEnginePassengerCar();
		InternalCombustionEngine engine;
		try {
			initializeCar(combustionCar, combustionCarElement);
			engine = initializeCombustionEngine(combustionCarElement);
			combustionCar.setCombustionEngine(engine);
		} catch (IllegalSetValueException exception) {
			LOG.error(exception.getMessage(), exception);
		}
		return combustionCar;
	}

	private PassengerCar buildElectricCar(Element electricCarElement) {
		PassengerCar electricCar = new ElectricPassengerCar();
		ElectricEngine engine;
		try {
			initializeCar(electricCar, electricCarElement);
			engine = initializeElectricEngine(electricCarElement);
			electricCar.setElectricEngine(engine);
		} catch (IllegalSetValueException exception) {
			LOG.error(exception.getMessage(), exception);
		}
		return electricCar;
	}

	private PassengerCar buildHybridCar(Element hybridCarElement) {
		PassengerCar hybridCar = new HybridPassengerCar();
		InternalCombustionEngine comEngine;
		ElectricEngine elEngine;
		try {
			initializeCar(hybridCar, hybridCarElement);
			comEngine = initializeCombustionEngine(hybridCarElement);
			elEngine = initializeElectricEngine(hybridCarElement);
			hybridCar.setCombustionEngine(comEngine);
			hybridCar.setElectricEngine(elEngine);
		} catch (IllegalSetValueException exception) {
			LOG.error(exception.getMessage(), exception);
		}
		return hybridCar;
	}

	private void initializeCar(PassengerCar car, Element carElement)
			throws IllegalSetValueException {
		car.setCarID(Integer.parseInt(carElement.getAttribute("id")
				.substring(1)));
		car.setCarPrice(Integer.parseInt(getElementTextContent(carElement,
				CarEnum.PRICE.getValue())));
		car.setTopSpeed(Integer.parseInt(getElementTextContent(carElement,
				CarEnum.SPEED.getValue())));
		car.setCurbWeight(Integer.parseInt(getElementTextContent(carElement,
				CarEnum.WEIGHT.getValue())));
		car.setConsumption(Double.parseDouble(getElementTextContent(carElement,
				CarEnum.CONSUMPTION.getValue())));
		car.setBodyStyle(BodyStyle.valueOf(getElementTextContent(carElement,
				CarEnum.BODYSTYLE.getValue()).toUpperCase()));
		car.setDriveArrangement(DriveArrangement.valueOf(getElementTextContent(
				carElement, CarEnum.DRIVE.getValue())));
		car.setGearbox(Gearbox.valueOf(getElementTextContent(carElement,
				CarEnum.GEARBOX.getValue()).toUpperCase()));
	}

	private InternalCombustionEngine initializeCombustionEngine(
			Element carElement) throws IllegalSetValueException {
		InternalCombustionEngine engine;
		Element combustionEngine = (Element) carElement.getElementsByTagName(
				Constants.ENGINE).item(0);

		if (combustionEngine.getAttribute(Constants.FUEL).equals(
				Constants.PETROL)) {
			engine = new PetrolEngine();
		} else {
			engine = new DieselEngine();
		}
		engine.setNumberOfCylinders(Integer.parseInt(getElementTextContent(
				combustionEngine, CarEnum.CYLINDERS.getValue())));
		engine.setDisplacement(Integer.parseInt(getElementTextContent(
				combustionEngine, CarEnum.DISPLACEMENT.getValue())));
		engine.setMaxPower(Integer.parseInt(getElementTextContent(
				combustionEngine, CarEnum.POWER.getValue())));
		engine.setMaxTorque(Integer.parseInt(getElementTextContent(
				combustionEngine, CarEnum.TORQUE.getValue())));
		engine.setTurbocharged(Boolean.parseBoolean(getElementTextContent(
				combustionEngine, CarEnum.TURBO.getValue())));
		return engine;
	}

	private ElectricEngine initializeElectricEngine(Element carElement)
			throws IllegalSetValueException {
		ElectricEngine engine = new ElectricEngine();
		Element electricEngine;
		
		if (carElement.getTagName().equals(Constants.ELECTRIC_CAR)) {
			electricEngine = (Element) carElement.getElementsByTagName(
					Constants.ENGINE).item(0);
		} else {
			electricEngine = (Element) carElement.getElementsByTagName(
					Constants.ENGINE).item(1);
		}
		engine.setMaxPower(Integer.parseInt(getElementTextContent(
				electricEngine, CarEnum.EPOWER.getValue())));
		engine.setMaxTorque(Integer.parseInt(getElementTextContent(
				electricEngine, CarEnum.ETORQUE.getValue())));
		return engine;
	}

	private String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
