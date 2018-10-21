package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;

public interface WeatherClient {
	public abstract BigDecimal getTemperatuur(String city);
}
