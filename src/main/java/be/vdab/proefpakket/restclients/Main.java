package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

class Main {
	@JsonProperty("temp")
	private BigDecimal temp;

	public BigDecimal getTemp() {
		return temp;
	}

	public void setTemp(BigDecimal temp) {
		this.temp = temp;
	}
	
}
