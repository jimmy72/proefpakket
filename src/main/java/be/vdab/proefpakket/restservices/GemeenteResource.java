package be.vdab.proefpakket.restservices;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;

import org.springframework.hateoas.EntityLinks;
import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonAutoDetect.Visibility;

import be.vdab.proefpakket.entities.Gemeente;

@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
@JsonAutoDetect(fieldVisibility = Visibility.ANY)
class GemeenteResource extends ResourceSupport {
	@SuppressWarnings("unused")
	private Gemeente gemeente;
	
	GemeenteResource() {} // JAXB heeft een default constructor nodig
	
	GemeenteResource(Gemeente gemeente, EntityLinks entityLinks) {
		this.gemeente = gemeente;
		this.add(entityLinks.linkToSingleResource(Gemeente.class, gemeente.getId())); 
	}
}
