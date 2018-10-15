package be.vdab.proefpakket.constraints;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class OndernemingsNummerValidator implements ConstraintValidator<OndernemingsNummer, Long>{

	@Override
	public boolean isValid(Long ondernemingsNummer, ConstraintValidatorContext context) {
		if(ondernemingsNummer == null) {
			return true;
		}
		int laatsteTweeCijfers = (int) (ondernemingsNummer % 100);
		int eersteZevenCijfers = (int) (ondernemingsNummer / 100);
		int rest = eersteZevenCijfers % 97;
		return laatsteTweeCijfers == 97 - rest;
	}

	
	
	
}
