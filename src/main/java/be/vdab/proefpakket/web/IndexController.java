package be.vdab.proefpakket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.proefpakket.services.BrouwerService;

@Controller
@RequestMapping("/")
public class IndexController {
	private static final String VIEW = "index";
	private final BrouwerService brouwerService;
	//private final char[] alfabet = "ABCDEFGHIJKLMNOPQRSTUVWXYZ".toCharArray();
	
	
	
	IndexController(BrouwerService brouwerService) {
		this.brouwerService = brouwerService;
	}

	@GetMapping
	ModelAndView index() {
		return new ModelAndView(VIEW, "alfabet", this.brouwerService.findBeginLetters());
		//return new ModelAndView(VIEW, "alfabet", this.alfabet);
	}
	
	@GetMapping(params = "letter")
	ModelAndView beginletter(String letter) {
		return new ModelAndView(VIEW, "alfabet", this.brouwerService.findBeginLetters())
				.addObject("brouwers", brouwerService.findByBeginNaam(letter));
	}
}
