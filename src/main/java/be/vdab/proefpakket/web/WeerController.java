package be.vdab.proefpakket.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import be.vdab.proefpakket.exceptions.KanTemperatuurNietLezenException;
import be.vdab.proefpakket.services.WeerService;

@Controller
@RequestMapping("/gemeenten")
class WeerController {
	private static final String VIEW = "gemeenten/temperatuur";
	private final WeerService weerService;
	
	WeerController(WeerService weerService) {
		this.weerService = weerService;
	}
	
	@GetMapping(path = "/{gemeente}/temperatuur")
	ModelAndView gemeenteTemperatuur(@PathVariable(name = "gemeente") String gemeente) {
		ModelAndView modelAndView = new ModelAndView(VIEW);
		try {
			modelAndView.addObject("gemeenteTemperatuur", new GemeenteTemperatuur(gemeente, this.weerService.getTemperature(gemeente)));
		}catch(KanTemperatuurNietLezenException ex) {
			modelAndView.addObject("fout", ex.getMessage());
		}
		return modelAndView;
	}
	
	
}
