package be.vdab.proefpakket.services;

import java.math.BigDecimal;

public interface WeerService {
	public abstract BigDecimal getTemperature(String city);
}
