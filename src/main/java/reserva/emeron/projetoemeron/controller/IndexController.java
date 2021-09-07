package reserva.emeron.projetoemeron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.service.ReservaService;

@Controller
public class IndexController {
	
	
	@Autowired
	private ReservaService reservaService;
	
	
@GetMapping("/")
	public ModelAndView listarReservas() {
	
	
	List<Reserva> reservaList = this.reservaService.buscarTodos();

	ModelAndView mv = new ModelAndView("reserva/listareservas.html");
	mv.addObject("reservas", reservaList);
	return mv;
				
	}
}
