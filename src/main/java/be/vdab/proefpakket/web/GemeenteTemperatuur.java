package be.vdab.proefpakket.web;

import java.math.BigDecimal;

import org.springframework.format.annotation.NumberFormat;

class GemeenteTemperatuur {
	private final String gemeente;
	@NumberFormat
	private final BigDecimal temperatuur;
	
	GemeenteTemperatuur(String gemeente, BigDecimal temperatuur) {
		this.gemeente = gemeente;
		this.temperatuur = temperatuur;
	}
	public String getGemeente() {
		return gemeente;
	}
	public BigDecimal getTemperatuur() {
		return temperatuur;
	}
	
}
