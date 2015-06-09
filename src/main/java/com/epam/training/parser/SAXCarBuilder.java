package com.epam.training.parser;

import java.io.IOException;

import org.apache.log4j.Logger;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.epam.training.parser.handler.CarHandler;

/* 
 * The class builds a list of taxi cars. The data is stored
 * in the XML file. SAX parser is used to extract the data.  
 */
public class SAXCarBuilder extends AbstractCarBuilder {
	/* getting the logger reference */
	private static final Logger LOG = Logger.getLogger(SAXCarBuilder.class);
	private CarHandler carHandler;
	private XMLReader xmlReader;

	/* creating XML reader and setting the ContentHandler */
	public SAXCarBuilder() {
		super();
		this.carHandler = new CarHandler();
		try {
			this.xmlReader = XMLReaderFactory.createXMLReader();
			xmlReader.setContentHandler(carHandler);
		} catch (SAXException exception) {
			LOG.error("Error. No default XMLReader class was identified!",
					exception);
		}
	}

	@Override
	public void buildTaxiFleet(String filePath) {
		try {
			/* parsing the XML file */
			xmlReader.parse(filePath);
		} catch (SAXException exception) {
			LOG.error("SAX parser error!", exception);
		} catch (IOException exception) {
			LOG.error("I/O stream error!", exception);
		}
		/* getting the taxi fleet from a CarHandler */
		this.taxiFleet = carHandler.getTaxiFleet(); 
	}
}
