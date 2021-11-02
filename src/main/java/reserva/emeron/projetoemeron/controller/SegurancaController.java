package reserva.emeron.projetoemeron.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class SegurancaController {
	
	
	
	@GetMapping("/403")
	public String acessoNegado() {
		
		
		return "403";
	}

}
