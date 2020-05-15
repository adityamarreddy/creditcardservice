package com.bank.creditcard.generator;

public class NumGenerator {

	private NumGenerator() {
		throw new AssertionError("Instantiation now allowed");
	}

	public static int generateRandomNumber() {
		return (int) (Math.random() * 10);
	}

	public static long generateCreditCardNumber() {
		StringBuilder builder = new StringBuilder();
		for (int i = 0; i < 16; i++) {
			builder.append((int) (Math.random() * 10));
		}
		return new Long(builder.toString());
	}

}
