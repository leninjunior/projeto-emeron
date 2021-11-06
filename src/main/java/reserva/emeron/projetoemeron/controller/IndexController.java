package reserva.emeron.projetoemeron.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.service.CursoService;
import reserva.emeron.projetoemeron.service.ProfessorService;
import reserva.emeron.projetoemeron.service.ReservaService;
import reserva.emeron.projetoemeron.service.RoleService;
import reserva.emeron.projetoemeron.service.UsuarioService;

@Controller
public class IndexController {
	
	

	
	@Autowired
	private CursoService cursoService;
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	
	@Autowired
	private ReservaService reservaService;
	
	@Autowired
	private ProfessorService professorService;
	
	@GetMapping("/todasreservas")
	public ModelAndView listarReservas() {
	
	
	List<Reserva> reservaList = this.reservaService.buscarTodos();

	ModelAndView mv = new ModelAndView("reserva/listareservas.html");
	mv.addObject("reservas", reservaList);
	return mv;
				
	
	}
	
	@GetMapping("/todasreserva1s")
	public ModelAndView todasReservas() {
	 
	//TESTECOMRESERVAVIACARD
	List<Reserva> reservaList = this.reservaService.buscarTodos();
	
	ModelAndView mv = new ModelAndView("todasreservas.html");
	mv.addObject("reservas1", reservaList);
	return mv;
				
	}

@GetMapping("/dashboard")
public ModelAndView testeDashbord() {
	
	
	 ModelAndView mv = new ModelAndView("admin/dashboard/index.html");
	 mv.addObject("quantidadecurso", cursoService.countCursos());
	 mv.addObject("quantidadereserva", reservaService.countReserva());
	 mv.addObject("quantidadeusuarios", usuarioService.countUsuarios());
	 mv.addObject("reservaanalise", reservaService.countReservaAnalise());
	 mv.addObject("reservacancelado", reservaService.countReservaCancelado());
	 mv.addObject("reservaconfirmada", reservaService.countReservaConfirmado());
	 mv.addObject("grupos", roleService.buscarTodosPerfil());
	 mv.addObject("quantidadeprofessores", professorService.countprofessors());
	
	 
	
	
	return mv;
}

@GetMapping("/login")
public String testepagina() {
	
	return "admin/auth/login.html";
}


}
