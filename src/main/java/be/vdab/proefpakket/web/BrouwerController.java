package be.vdab.proefpakket.web;

import java.util.Optional;

import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.DataBinder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import be.vdab.proefpakket.entities.Bestelling;
import be.vdab.proefpakket.entities.Brouwer;
import be.vdab.proefpakket.services.BestellingService;
import be.vdab.proefpakket.services.BrouwerService;
import be.vdab.proefpakket.services.GemeenteService;

@Controller
@RequestMapping("brouwers")
@SessionAttributes("bestelling")
public class BrouwerController {
	private static final String BROUWER_VIEW = "brouwers/brouwer";
	private static final String BROUWER_ONDERNEMINGSNUMMER_VIEW = "brouwers/ondernemingsnummer";
	private static final String REDIRECT_URL_BROUWER_NIET_GEVONDEN = "redirect:/";
	private static final String REDIRECT_NA_ONDERNEMINGSNR = "redirect:/brouwers/{id}";
	private static final String PROEFPAKKET_STAP1_VIEW = "brouwers/proefpakket/stap1";
	private static final String PROEFPAKKET_STAP2_VIEW = "brouwers/proefpakket/stap2";
	private final BrouwerService brouwerService;
	private final GemeenteService gemeenteService;
	private final BestellingService bestellingService;
	
	BrouwerController(BrouwerService brouwerService, GemeenteService gemeenteService, BestellingService bestellingService ) {
		this.brouwerService = brouwerService;
		this.gemeenteService = gemeenteService;
		this.bestellingService = bestellingService;
	}

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
			OndernemingsNummerForm form = new OndernemingsNummerForm();
			form.setOndernemingsNummer(brouwer.get().getOndernemingsNr());
			return new ModelAndView(BROUWER_ONDERNEMINGSNUMMER_VIEW)
					.addObject("brouwer", brouwer.get())
					.addObject("ondernemingsNummerForm", form);
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_URL_BROUWER_NIET_GEVONDEN);
	}
	
	@PostMapping(value = "{brouwer}/ondernemingsnummer")
	ModelAndView bewaarOndernemingsNummer(@PathVariable Optional<Brouwer> brouwer, 
			@Valid OndernemingsNummerForm form,
			BindingResult bindingResult, 
			RedirectAttributes redirectAttributes) {
		if(brouwer.isPresent()) {
			if(bindingResult.hasErrors()) {
				
				return new ModelAndView(BROUWER_ONDERNEMINGSNUMMER_VIEW).addObject("brouwer", brouwer.get());
			}
			brouwer.get().setOndernemingsNr(form.getOndernemingsNummer());
			brouwerService.update(brouwer.get());
			System.out.println("gelukt");
			redirectAttributes.addAttribute("id", brouwer.get().getId());
			return new ModelAndView(REDIRECT_NA_ONDERNEMINGSNR);
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_URL_BROUWER_NIET_GEVONDEN);
	}
	
	@GetMapping(value = "{brouwer}/proefpakket")
	ModelAndView proefPakket(@PathVariable Optional<Brouwer> brouwer, 
			RedirectAttributes redirectAttributes) {
		
		if(brouwer.isPresent()) {
			return new ModelAndView(PROEFPAKKET_STAP1_VIEW)
					.addObject("brouwer", brouwer.get())
					.addObject("bestelling", new Bestelling());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_URL_BROUWER_NIET_GEVONDEN);
		
	}
	
	@PostMapping(value = "{brouwer}/proefpakket", params = "stap2")
	ModelAndView proefPakketStap1NaarStap2(@PathVariable Optional<Brouwer> brouwer, @Validated(Bestelling.Stap1.class) Bestelling bestelling, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
		if (brouwer.isPresent()) {
			if (bindingResult.hasErrors()) {
				return new ModelAndView(PROEFPAKKET_STAP1_VIEW).addObject("brouwer", brouwer.get());
			}
			return new ModelAndView(PROEFPAKKET_STAP2_VIEW)
			.addObject("brouwer", brouwer.get())
			.addObject("gemeenten", gemeenteService.findAll());
		}
		redirectAttributes.addAttribute("fout", "Brouwer niet gevonden");
		return new ModelAndView(REDIRECT_URL_BROUWER_NIET_GEVONDEN);
	}
	
	@InitBinder("bestelling")
	void initBinder(DataBinder binder) {
		binder.initDirectFieldAccess();
	}
	
}
