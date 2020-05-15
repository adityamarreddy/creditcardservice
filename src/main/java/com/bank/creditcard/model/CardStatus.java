package com.bank.creditcard.model;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum CardStatus {
	

	ACTIVE("Active"),

	DELINQUENT("Delinquent"),

	CLOSED("Closed");

	private String value;

	CardStatus(String value) {
		this.value = value;
	}

	@Override
	@JsonValue
	public String toString() {
		return String.valueOf(value);
	}

	@JsonCreator
	public static CardStatus fromValue(String text) {
		for (CardStatus b : CardStatus.values()) {
			if (String.valueOf(b.value).equals(text)) {
				return b;
			}
		}
		return null;
	}


}
