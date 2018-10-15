package be.vdab.proefpakket.web;

import java.util.Optional;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Brouwer;

@Controller
@RequestMapping("brouwers")
public class BrouwerController {
	private static final String BROUWER_VIEW = "brouwers/brouwer";
	private static final String REDIRECT_URL_BROUWER_NIET_GEVONDEN = "redirect:/";
	
	@GetMapping(value = "{brouwer}")
	ModelAndView readBrouwer(@PathVariable Optional<Brouwer> brouwer, 
			RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW, "brouwer", brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_URL_BROUWER_NIET_GEVONDEN);
	}
	
	@GetMapping(value = "{brouwer}/ondernemingsnummer")
	ModelAndView ondernemingsNummer(@PathVariable Optional<Brouwer> brouwer, 
			RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			return new ModelAndView(BROUWER_VIEW, "brouwer", brouwer.get());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_URL_BROUWER_NIET_GEVONDEN);
	}
}
