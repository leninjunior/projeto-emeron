package reserva.emeron.projetoemeron.controller;

import java.util.List;

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
import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.model.Usuario;
import reserva.emeron.projetoemeron.repository.UsuarioRepository;
import reserva.emeron.projetoemeron.service.CursoService;
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
	
	
	
	@GetMapping("/novo")
	private ModelAndView reserva() {
		 
		Usuario usuario = usuarioService.getUser();	 
		
		
		List<Curso> cursoList = this.cursoService.buscarTodos();
		List<Reserva> reservaList = this.reservaService.buscarTodos();
		
		
		
		ModelAndView mv = new ModelAndView("reserva/reservaform.html");
		mv.addObject("usuarioid", usuario.getId());
		mv.addObject("cursolist", cursoList);
		mv.addObject("reservalist", reservaList);
	
		return mv;
	}
	

	
	@PostMapping("/add/salvar")
	private String salvar(@Valid Reserva reserva, BindingResult result, RedirectAttributes redirect) {
		
		Usuario usuario = usuarioService.getUser();	 
		reserva.setUsuario(usuario);
			
		if(result.hasErrors()){
			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatorios "); //mensagem na view
			return "redirect:/reserva/novo"; //na rota
		}
		
		
		redirect.addFlashAttribute("mensagemsucesso", "Reserva feita com Sucesso!");
		reservaService.salvarDados(reserva);
		//reservaRepository.save(reserva);
		
		return "redirect:/reserva/novo";
	}
	
	
	
	
	
//	@GetMapping("/listar")
//	private ModelAndView listarReserva() {
//		
//		List<Reserva> reservaList =  reservaService.buscarTodos();
//		ModelAndView mv = new ModelAndView("reserva/listareservas.html");
//		mv.addObject("reservas", reservaList);
//		
//		return mv;
//		
//	}



	@GetMapping("{id}")
	private ModelAndView editCurso(@PathVariable("id") Long id) {
		Iterable<Reserva> reservaIT = this.reservaService.buscarTodos();
		List<Curso> cursoList = cursoService.buscarTodos();
		ModelAndView mv = new ModelAndView("reserva/reservaform.html");
		mv.addObject("reservaList", reservaIT);
		mv.addObject("cursolist", cursoList);
		
		
		Reserva reservaEdit = this.reservaService.findById(id);

		mv.addObject("reserva", reservaEdit);

		return mv;
	}
	

}



