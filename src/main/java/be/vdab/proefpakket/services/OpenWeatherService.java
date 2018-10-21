package be.vdab.proefpakket.services;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import be.vdab.proefpakket.restclients.WeatherClient;

@Service
class OpenWeatherService implements WeerService{

	private final WeatherClient weatherClient;
	
	
	OpenWeatherService(WeatherClient weatherClient) {
		this.weatherClient = weatherClient;
	}


	@Override
	public BigDecimal getTemperature(String city) {
		return weatherClient.getTemperatuur(city);
	}

}
