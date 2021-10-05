package reserva.emeron.projetoemeron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.service.CursoService;
import reserva.emeron.projetoemeron.service.ReservaService;

@Controller
public class IndexController {
	
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private CursoService cursoService;
	
	
@GetMapping("/")
	public ModelAndView listarReservas() {
	
	
	List<Reserva> reservaList = this.reservaService.buscarTodos();

	ModelAndView mv = new ModelAndView("reserva/listareservas.html");
	mv.addObject("reservas", reservaList);
	return mv;
				
	}

@GetMapping("/index")
public ModelAndView testeDashbord() {
	
	
	 ModelAndView mv = new ModelAndView("admin/dashboard/index.html");
	 mv.addObject("quantidade", cursoService.countCursos());
	 System.out.println("quanridade de cyurss são>>>" +cursoService.countCursos());
	 
	
	
	return mv;
}

@GetMapping("/login")
public String testepagina() {
	
	return "admin/auth/login.html";
}


}
