package com.epam.training.parser;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.training.parser.handler.CarHandler;

public class SAXCarBuilder extends AbstractCarBuilder {
	/* getting the logger reference */
	private static final Logger LOG = Logger.getLogger(SAXCarBuilder.class);
	private CarHandler carHandler;
	private XMLReader xmlReader;
	
	public SAXCarBuilder() {
		super();
		this.carHandler = new CarHandler();
		try {
			this.xmlReader = XMLReaderFactory.createXMLReader();
			xmlReader.setContentHandler(carHandler);
		} catch (SAXException exception) {
			LOG.error("Error. No default XMLReader class was identified!", exception);
		}
	}

	@Override
	public void buildTaxiFleet(String filePath) {
		try {
			xmlReader.parse(filePath);
		} catch (SAXException exception) {
			LOG.error("SAX parser error!", exception);
		} catch (IOException exception) {
			LOG.error("I/O stream error!", exception);
		}
		this.taxiFleet = carHandler.getTaxiFleet();
	}
}
