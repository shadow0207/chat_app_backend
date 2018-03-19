package com.stackroute.activitystream.utils;

public class UIDGenerator {

	private static UIDGenerator generatorInstance = new UIDGenerator();

	public UIDGenerator() {
		// TODO Auto-generated constructor stub
	}

	public static UIDGenerator getGeneratorInstance() {
		return generatorInstance;
	}

	public String generateUID(String param1, String param2) {
		System.err.println("ENTERING");
		String randValueString = "";
		if (param2 != null || !param2.isEmpty() || !param2.equals("")) {
			randValueString = param1.toUpperCase().charAt(0) + param2.toUpperCase().charAt(0)
					+ generateRandomNumberString();
		}else
		{
			randValueString = param1.toUpperCase().charAt(0) + param1.toUpperCase().charAt(1)
					+ generateRandomNumberString();
		}
		return randValueString;

	}

	public String generateRandomNumberString() {
		int randValue = (int) (Math.random() * ((9999 - 1000) + 1)) + 1000;
		return Integer.toString(randValue);
	}

}
