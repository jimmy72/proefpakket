package be.vdab.proefpakket.restservices;

import java.util.Optional;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ExposesResourceFor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import be.vdab.proefpakket.entities.Gemeente;
import be.vdab.proefpakket.exceptions.GemeenteNotFoundException;
import be.vdab.proefpakket.services.GemeenteService;

@RestController
@RequestMapping(path = "/gemeenten")
@ExposesResourceFor(Gemeente.class)
class GemeenteRestController {
	private final GemeenteService gemeenteService;
	private final EntityLinks entityLinks;
	
	GemeenteRestController(GemeenteService gemeenteService, EntityLinks entityLinks) {
		this.gemeenteService = gemeenteService;
		this.entityLinks = entityLinks;
	}
	
	@GetMapping(path = "/{id}.xml")
	GemeenteResource read(@PathVariable(name = "id") Optional<Gemeente> gemeente) { 
		if(gemeente.isPresent()) {
			//return filiaal.get();
			return new GemeenteResource(gemeente.get(), entityLinks);
		}
		throw new GemeenteNotFoundException();
	}
	
	@ExceptionHandler(GemeenteNotFoundException.class) 
	@ResponseStatus(HttpStatus.NOT_FOUND) 
	void gemeenteNietGevonden() {
	}
	
}
