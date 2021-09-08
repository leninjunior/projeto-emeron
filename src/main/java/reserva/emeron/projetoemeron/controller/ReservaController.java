package reserva.emeron.projetoemeron.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import reserva.emeron.projetoemeron.model.Curso;
import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.service.CursoService;
import reserva.emeron.projetoemeron.service.ReservaService;


@Controller
@RequestMapping("/reserva")
public class ReservaController {
	
	@Autowired
	private CursoService cursoService;

	@Autowired
	private ReservaService reservaService;

	
	
	
	
	@GetMapping("/novo")
	private ModelAndView curso() {
		
		ModelAndView mv = new ModelAndView("reserva/reservaform.html");
		List<Curso> cursoList = this.cursoService.buscarTodos();
		mv.addObject("cursolist", cursoList);
		return mv;
	}
	

	
	@PostMapping("/add/salvar")
	private String salvar(@Valid Reserva reserva, BindingResult result, RedirectAttributes redirect) {
		
		if(result.hasErrors()){
			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatorios "); //mensagem na view
			return "redirect:/reserva/novo"; //na rota
		}
		
		
		redirect.addFlashAttribute("mensagemsucesso", "Reserva feita com Sucesso!");
		reservaService.salvarDados(reserva);
		//reservaRepository.save(reserva);
		
		return "redirect:/reserva/novo";
	}
	
	
	@GetMapping("/listar")
	private ModelAndView listarReserva() {
		
		List<Reserva> reservaList =  reservaService.buscarTodos();
		ModelAndView mv = new ModelAndView("reserva/listareservas.html");
		mv.addObject("reservas", reservaList);
		
		return mv;
		
	}
	

}



