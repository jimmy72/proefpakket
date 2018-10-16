package be.vdab.proefpakket.web;

import javax.validation.constraints.NotNull;

import be.vdab.proefpakket.constraints.OndernemingsNummer;

public class OndernemingsNummerForm {

	@NotNull
	@OndernemingsNummer
	private Long ondernemingsNummer;

	public Long getOndernemingsNummer() {
		return ondernemingsNummer;
	}

	public void setOndernemingsNummer(Long ondernemingsNummer) {
		this.ondernemingsNummer = ondernemingsNummer;
	}
	
}
