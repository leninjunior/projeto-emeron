package reserva.emeron.projetoemeron.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	
	
	@GetMapping("/novo")
	private ModelAndView usuario() {
		
		ModelAndView mv = new ModelAndView("usuario/usuarioform.html");
	
		
		return mv;
	}
	

}
