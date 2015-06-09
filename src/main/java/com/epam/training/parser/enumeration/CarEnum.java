package com.epam.training.parser.enumeration;

/* the list of all fields in the 'PassengerCar' object */
public enum CarEnum {
	PRICE("price"), 
	SPEED("speed"), 
	WEIGHT("weight"), 
	CONSUMPTION("consumption"), 
	BODYSTYLE("bodystyle"), 
	DRIVE("drive"), 
	GEARBOX("gearbox"), 
	CYLINDERS("cylinders"),
	DISPLACEMENT("displacement"), 
	POWER("power"), 
	TORQUE("torque"), 
	TURBO("turbo"),
	EPOWER("epower"),
	ETORQUE("etorque"),
	ENGINE("engine");
	
	private String value;

	private CarEnum(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}
