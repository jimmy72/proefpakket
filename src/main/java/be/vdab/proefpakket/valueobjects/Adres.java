package be.vdab.proefpakket.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotBlank;

import org.hibernate.validator.constraints.SafeHtml;

import be.vdab.proefpakket.entities.Gemeente;

@Embeddable
public class Adres implements Serializable {
	private static final long serialVersionUID = 1L;

	@NotBlank 
	@SafeHtml
	private String straat;
	@NotBlank 
	@SafeHtml
	private String huisNr;
	
	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "gemeenteId")
	private Gemeente gemeente;

	public String getStraat() {
		return straat;
	}

	public String getHuisNr() {
		return huisNr;
	}

	public Gemeente getGemeente() {
		return gemeente;
	}
	
	
}
