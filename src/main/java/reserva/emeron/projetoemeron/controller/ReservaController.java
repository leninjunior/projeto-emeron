package reserva.emeron.projetoemeron.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import reserva.emeron.projetoemeron.model.Curso;
import reserva.emeron.projetoemeron.model.Locais;
import reserva.emeron.projetoemeron.model.Professor;
import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.model.ReservaStatus;
import reserva.emeron.projetoemeron.model.Usuario;
import reserva.emeron.projetoemeron.service.CursoService;
import reserva.emeron.projetoemeron.service.LocaisService;
import reserva.emeron.projetoemeron.service.ProfessorService;
import reserva.emeron.projetoemeron.service.ReservaService;
import reserva.emeron.projetoemeron.service.UsuarioService;


@Controller
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	private CursoService cursoService;

	@Autowired
	private ReservaService reservaService;

	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private LocaisService locaisService;
	
	@Autowired
	private ProfessorService professorService;
	
	
	
	@GetMapping("/novo")
	private ModelAndView reserva( Reserva reserva , HttpServletRequest request) {
		Usuario usuario = usuarioService.getUser();	 
		ModelAndView mv = new ModelAndView("reserva/reservaform.html");
		
		List<Reserva> reservaList = this.reservaService.buscarTodos();
		List<Curso> cursoList = this.cursoService.buscarTodosCursos();
		
		List<Locais> locaisList =  this.locaisService.buscarTodosLocais();
		List<Professor> professorList = professorService.buscarTodosProfessores();
		
		mv.addObject("listprofessores", professorList);
		
		if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_DEPED")) {

			mv.addObject("reservalist", reservaList);
			
		} else {
			
			List<Reserva> reservaPorIdUsuario = reservaService.buscarReservaPorUsuario(usuario);
			mv.addObject("reservalist", reservaPorIdUsuario);
		
		}
		
		
		
		
		
		
		
		
		
		mv.addObject("usuarioid", usuario.getId());
		mv.addObject("cursolist", cursoList);
		mv.addObject("locaislist", locaisList);
		
	
		return mv;
	}
	

	
	@PostMapping("add/salvar")
	private String salvar(@Valid Reserva reserva, BindingResult result, RedirectAttributes redirect, HttpServletRequest request) {
		
		
		if(reservaService.localJaReservado(reserva) == true || reservaService.localJaReservado1(reserva)== true) {
		
			redirect.addFlashAttribute("mensagem", "Local, Data, Hora Inicial e Hora final já cadastradas. Verifique todas as reservas. "); // mensagem na view
			return "redirect:/reserva/novo"; // na rota
		
			
		}
		
		Usuario usuario = usuarioService.getUser();	 
		
		reserva.setUsuario(usuario);
		
		
			
		if (result.hasErrors()) {

			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatorios "); // mensagem na view
			return "redirect:/reserva/novo"; // na rota
		}

		try {

			if (reserva.getId() == null || (reserva.getId() != null && reserva.getId() <= 0)) {

				if (reservaService.reservaExiste(reserva.getNome()) == false) {

					if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_DEPED")) {
						

						reservaService.salvarDados(reserva);
						redirect.addFlashAttribute("mensagemsucesso",
								"Reserva  Adicionado com Sucesso!");
					} else {

						reserva.setReservaStatus(ReservaStatus.ANALISE);
						reservaService.salvarDados(reserva);

						redirect.addFlashAttribute("mensagemsucesso", "Reserva  Adicionado com Sucesso!" );
					}

				} else { 
					
					
					redirect.addFlashAttribute("mensagemsucesso", "Reserva já Existente, por favor, verifique!" ); 
					return "redirect:/reserva/novo"; // na rota
					
				
				}
				

			} 
			 
			 
			
			else {
				
				if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_DEPED")) {
				
					reservaService.adminUpdate(reserva);
					redirect.addFlashAttribute("mensagemeditado", "Reserva Editado com Sucesso!");
					return "redirect:/reserva/novo"; // na rota

				}
				
				

				reservaService.usuarioUpdate(reserva);
				redirect.addFlashAttribute("mensagemeditado", "Reserva Editado com Sucesso!!");

			}

			/*
			 * else {
			 * 
			 * 
			 * redirect.addFlashAttribute("mensagemeditado",
			 * "Reserva Editado com Sucesso!"); }
			 */
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("mensagemiguais", "Data da reserva não pode estar vazia!");
		}
		return "redirect:/reserva/novo"; // na rota
	}
	
	/*
	 * 
	 */	
	
	/*
	 * @PostMapping("/add/update") private String updateReserva(Reserva reserva) {
	 * 
	 * 
	 * Usuario usuario = usuarioService.getUser(); usuario.getUsername();
	 * 
	 * System.out.println( ">>>>>>>>>>>>>>>>" + usuario.getUsername());
	 * 
	 * reservaService.updateTeste(reserva);
	 * 
	 * return "redirect:/reserva/novo"; // na rota }
	 */
		
		
		
		
		
		
		
//		redirect.addFlashAttribute("mensagemsucesso", "Reserva feita com Sucesso!");
//		reservaService.salvarDados(reserva);
//		//reservaRepository.save(reserva);
//		
//		return "redirect:/reserva/novo";
	
	
	
	
	
	
	@GetMapping("/listar")
	private ModelAndView listarReserva() {
		
		List<Reserva> reservaList =  reservaService.buscarTodos();
		ModelAndView mv = new ModelAndView("reserva/listareservas.html");
		mv.addObject("reservalist", reservaList);
		
		return mv;
		
	}



	@GetMapping("{id}")
	private ModelAndView editReserva(@PathVariable("id") Long id,  HttpServletRequest request ) {
		Usuario usuario = usuarioService.getUser();	 
		List<Locais> locaisList = this.locaisService.buscarTodosLocais();
		List<Reserva> reservaList = this.reservaService.buscarTodos();

		ModelAndView mv = new ModelAndView("reserva/reservaform.html");
	List<Professor> professorList = professorService.buscarTodosProfessores();
	List<Curso> cursoList = this.cursoService.buscarTodosCursos();
		
		

		if (request.isUserInRole("ROLE_ADMIN") || request.isUserInRole("ROLE_DEPED")) {

			mv.addObject("reservalist", reservaList);
			mv.addObject("listprofessores", professorList);
			mv.addObject("cursolist", cursoList);
				mv.addObject("locaislist", locaisList);
			
		}else {
			List<Reserva> reservaPorIdUsuario = reservaService.buscarReservaPorUsuario(usuario);
			mv.addObject("reservalist", reservaPorIdUsuario);
		}
		
		mv.addObject("listprofessores", professorList);
		mv.addObject("cursolist", cursoList);
			mv.addObject("locaislist", locaisList);
		


	
		

		Reserva reservaEdit = this.reservaService.findById(id);

		mv.addObject("reserva", reservaEdit);

		return mv;
	}
	
	@GetMapping("/minhasreservas")
	private ModelAndView listarReservaPorUsuario() {

		Usuario usuario = usuarioService.getUser();

		List<Reserva> reservaPorIdUsuario = reservaService.buscarReservaPorUsuario(usuario);

		ModelAndView mv = new ModelAndView("reserva/minhasreservas.html");

		mv.addObject("reservalist", reservaPorIdUsuario);

		return mv;
	}
	
	
	
	@GetMapping("/reservaspendentes")
	private ModelAndView reservasPendentes() {

		Usuario usuario = usuarioService.getUser();	 
		ModelAndView mv = new ModelAndView("reserva/reservaspendentes.html");
		
		List<Reserva> reservaList = this.reservaService.reservaEmAnalise();
		List<Curso> cursoList = this.cursoService.buscarTodosCursos();
		
		List<Locais> locaisList =  this.locaisService.buscarTodosLocais();
	List<Professor> professorList = professorService.buscarTodosProfessores();
		
		mv.addObject("listprofessores", professorList);
		
		
		mv.addObject("reservalist", reservaList);
		mv.addObject("usuarioid", usuario.getId());
		mv.addObject("cursolist", cursoList);
		mv.addObject("locaislist", locaisList);
	

		return mv;
	}
	
	
	
	@GetMapping("/confirmar/{id}")
	private String aceitarReserva(Reserva id) {
		
		reservaService.confirmarReserva(id);
		
		return "redirect:/reserva/reservaspendentes"; // na rota
	}
	
	@GetMapping("/cancelar/{id}")
	private String cancelarReserva(Reserva id) {
		
		reservaService.cancelarReserva(id);
		
		return "redirect:/reserva/reservaspendentes"; // na rota
	}
	
	
	/*
	 * @ModelAttribute("todosStatusReserva") public List<ReservaStatus>
	 * todosStatusReserva() { return Arrays.asList(ReservaStatus.values()); }
	 */
}


