package reserva.emeron.projetoemeron.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import reserva.emeron.projetoemeron.model.Locais;
import reserva.emeron.projetoemeron.service.LocaisService;

@Controller
@RequestMapping("/local")
public class LocaisController {
	
	
	@Autowired
	private LocaisService locaisService;
	
	
	@GetMapping("/novo")
	public String indexLocal() {
		
		return "locais/localform.html";
	}
	

	@PostMapping("/salvar")
	public String salvarLocais(Locais local) {
		
		locaisService.salvarLocal(local);
		
		
		return "redirect:/local/novo";
	}

	
	
}
