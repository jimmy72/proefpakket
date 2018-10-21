package be.vdab.proefpakket.restclients;

import java.math.BigDecimal;
import java.net.URI;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import be.vdab.proefpakket.exceptions.KanTemperatuurNietLezenException;

@Component
class OpenWeatherMapClient implements WeatherClient{

	private static final Logger LOGGER = LoggerFactory.getLogger(OpenWeatherMapClient.class);
	private final String weatherURL;
	private final RestTemplate restTemplate;
	
	OpenWeatherMapClient(@Value("${openWeatherMapURL}") String weatherURL, RestTemplateBuilder restTemplateBuilder){
		this.weatherURL = weatherURL;
		this.restTemplate = restTemplateBuilder.build();
	}
	
	@Override
	public BigDecimal getTemperatuur(String city) {
		try {
			Weer weer = restTemplate.getForObject(weatherURL, Weer.class, city);
			return weer.getMain().getTemp();
		}catch(RestClientException ex) {
			LOGGER.error("Kan temperatuur niet lezen", ex);
			throw new KanTemperatuurNietLezenException("Kan temperatuur niet lezen");
		}
		
	}

}
