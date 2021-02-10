package com.exercise.persistence.enumeration;

public enum Currency {

	EUR("Euro", "€", "EUR"), USD("United states dollar", "$", "USD")
	
	;
	
	final String name;
	
	final String symbol;
	
	final String abbr;

	private Currency(String name, String symbol, String abbr) {
		this.name = name;
		this.symbol = symbol;
		this.abbr = abbr;
	}

	public String getName() {
		return name;
	}

	public String getSymbol() {
		return symbol;
	}

	public String getAbbr() {
		return abbr;
	}
}
