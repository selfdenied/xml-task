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
import com.epam.training.parser.enumeration.CarEnum;

/* 
 * The class builds a list of taxi cars. The data is stored
 * in the XML file. DOM parser is used to extract the data.  
 */
public class DOMCarBuilder extends AbstractCarBuilder {
	/* getting the logger reference */
	private static final Logger LOG = Logger.getLogger(DOMCarBuilder.class);
	private DocumentBuilder documentBuilder;

	/* initialize the factory and get new DocumentDuilder */
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
			/* parsing the XML and creating the document (tree hierarchy) */
			document = documentBuilder.parse(filePath);
			Element root = document.getDocumentElement();
			/* getting the node lists from the root element */
			/* here we have 3 types of cars (Combustion, Electric and Hybrid) */
			NodeList combustionCarsList = root.getElementsByTagName(Constants.COMBUSTION_CAR);
			NodeList electricCarsList = root.getElementsByTagName(Constants.ELECTRIC_CAR);
			NodeList hybridCarsList = root.getElementsByTagName(Constants.HYBRID_CAR);

			/* build combustion cars and add them to the taxi fleet list */
			for (int i = 0; i < combustionCarsList.getLength(); i++) {
				Element combustionCarElement = (Element) combustionCarsList.item(i);
				PassengerCar combustionCar = buildCombustionCar(combustionCarElement);
				taxiFleet.add(combustionCar);
			}
			/* build electric cars and add them to the taxi fleet list */
			for (int i = 0; i < electricCarsList.getLength(); i++) {
				Element electricCarElement = (Element) electricCarsList.item(i);
				PassengerCar electricCar = buildElectricCar(electricCarElement);
				taxiFleet.add(electricCar);
			}
			/* build hybrid cars and add them to the taxi fleet list */
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

	/*
	 * supplementary method that creates a combustion car and initializes its
	 * fields
	 */
	private PassengerCar buildCombustionCar(Element combustionCarElement) {
		PassengerCar combustionCar = new CombustionEnginePassengerCar();
		InternalCombustionEngine engine = initializeCombustionEngine(combustionCarElement);

		initializeCar(combustionCar, combustionCarElement);
		combustionCar.setCombustionEngine(engine);
		return combustionCar;
	}

	/*
	 * supplementary method that creates an electric car and initializes its
	 * fields
	 */
	private PassengerCar buildElectricCar(Element electricCarElement) {
		PassengerCar electricCar = new ElectricPassengerCar();
		ElectricEngine engine = initializeElectricEngine(electricCarElement);

		initializeCar(electricCar, electricCarElement);
		electricCar.setElectricEngine(engine);
		return electricCar;
	}

	/* supplementary method that creates a hybrid car and initializes its fields */
	private PassengerCar buildHybridCar(Element hybridCarElement) {
		PassengerCar hybridCar = new HybridPassengerCar();
		InternalCombustionEngine comEngine = initializeCombustionEngine(hybridCarElement);
		ElectricEngine elEngine = initializeElectricEngine(hybridCarElement);

		initializeCar(hybridCar, hybridCarElement);
		hybridCar.setCombustionEngine(comEngine);
		hybridCar.setElectricEngine(elEngine);
		return hybridCar;
	}

	/* supplementary method that initializes the fields of the car object */
	/* the same for all types of cars */
	private void initializeCar(PassengerCar car, Element carElement) {
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

	/* supplementary method that initializes a combustion engine */
	private InternalCombustionEngine initializeCombustionEngine(
			Element carElement) {
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

	/* supplementary method that initializes an electric engine */
	private ElectricEngine initializeElectricEngine(Element carElement) {
		ElectricEngine engine = new ElectricEngine();
		Element electricEngine;

		/*
		 * this if-else block is needed to differentiate between electric cars
		 * and hybrids (hybrids have an electric motor on the second position)
		 */
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

	/* supplementary method that retrieves the text content from a node element */
	private String getElementTextContent(Element element, String elementName) {
		NodeList nList = element.getElementsByTagName(elementName);
		Node node = nList.item(0);
		String text = node.getTextContent();
		return text;
	}
}
