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
import reserva.emeron.projetoemeron.service.CursoService;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	@Autowired
	private CursoService cursoService;
	
	
	@GetMapping("/novo")
	private ModelAndView curso( Curso curso) {
		
		ModelAndView mv = new ModelAndView("curso/cursoform.html");
	
		return mv;
	}
	
	
	
	@PostMapping("/salvar")
	private String salvar(@Valid Curso curso, BindingResult result, RedirectAttributes redirect) {
		
		if(result.hasErrors()){
			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatórios "); //mensagem na view
			return "redirect:/curso/novo"; //na rota
		}
		
		redirect.addFlashAttribute("mensagemsucesso", "Curso Adicionado com Sucesso!");
		cursoService.salvar(curso);
		//reservaService.salvarDados(reserva);
		
		//reservaRepository.save(reserva);
		
		return "redirect:/reserva/novo";
	}
	
	
	@GetMapping("/listar")
	private ModelAndView listarCursos() {
		
		List<Curso> cursoList =  cursoService.buscarTodos();
		ModelAndView mv = new ModelAndView("/curso/listacursos.html");
		mv.addObject("cursos", cursoList);
		
		return mv;
	}
	
}
