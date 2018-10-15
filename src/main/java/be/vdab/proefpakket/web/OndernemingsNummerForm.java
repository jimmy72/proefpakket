package be.vdab.proefpakket.web;

import javax.validation.constraints.NotNull;

import be.vdab.proefpakket.constraints.OndernemingsNummer;

public class OndernemingsNummerForm {

	@NotNull
	@OndernemingsNummer
	private Long ondernemingsNr;

	public Long getOndernemingsNr() {
		return ondernemingsNr;
	}

	public void setOndernemingsNr(Long ondernemingsNr) {
		this.ondernemingsNr = ondernemingsNr;
	}
	
}
