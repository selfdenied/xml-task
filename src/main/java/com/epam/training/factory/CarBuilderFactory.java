package com.epam.training.factory;

import org.apache.log4j.Logger;

import com.epam.training.parser.*;

/* the factory class that creates different car builders */
public class CarBuilderFactory {
	/* getting the logger reference */
	private static final Logger LOG = Logger.getLogger(CarBuilderFactory.class);

	/* the list of parsers available */
	private enum ParserType {
		SAX, DOM, STAX;
	}

	/* method returns the instance of AbstractCarBuilder sub-class */
	public static AbstractCarBuilder createCarBuilder(String parserType) {
		AbstractCarBuilder builder = new SAXCarBuilder();

		if (checkParserTypeName(parserType)) {
			ParserType type = ParserType.valueOf(parserType.toUpperCase());
			switch (type) {
			case SAX:
				builder = new SAXCarBuilder();
				break;
			case DOM:
				builder = new DOMCarBuilder();
				break;
			case STAX:
				builder = new StAXCarBuilder();
				break;
			default:
				LOG.error("Error. No such parser is available in the application!");
				break;
			}
		} else {
			LOG.warn("Warning. There is no such parser! SAX parser is used by default...");
		}
		return builder;
	}

	/* supplementary method that checks the validity of parser name */
	private static boolean checkParserTypeName(String parserType) {
		boolean parserNameOk = false;
		String type = parserType.toUpperCase();

		if (type.equals("SAX") | type.equals("STAX") | type.equals("DOM")) {
			parserNameOk = true;
		}
		return parserNameOk;
	}
}
