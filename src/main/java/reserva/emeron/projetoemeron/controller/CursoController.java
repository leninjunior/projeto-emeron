package reserva.emeron.projetoemeron.controller;

import java.util.List;
import java.util.Optional;

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
import reserva.emeron.projetoemeron.service.CursoService;
import reserva.emeron.projetoemeron.service.exception.NomeCursoJaCadastradoException;

@Controller
@RequestMapping("/curso")
public class CursoController {
	
	//private  static final String CADASTRO_CURSO = "curso/cursoform.html";
	 
	
	
	@Autowired
	private CursoService cursoService;
	
	
	@GetMapping("/novo")
	private ModelAndView curso( Curso curso) {
		
		ModelAndView mv = new ModelAndView("/curso/cursoform.html");
		List<Curso> cursoList =  cursoService.buscarTodos();
		
		mv.addObject("cursosAdmin", cursoList);
		
		return mv;
	

	}
	
	
	
	@PostMapping("/salvar")
	private String salvar(@Valid Curso curso, BindingResult result, RedirectAttributes redirect) {
		
		if(result.hasErrors()){
			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatórios "); //mensagem na view
			return "redirect:/curso/novo"; //na rota
		}
		
		try {
			
			cursoService.salvar(curso);
			redirect.addFlashAttribute("mensagemsucesso", "Curso Adicionado com Sucesso!");
			
		} catch (NomeCursoJaCadastradoException e) {
			
			redirect.addFlashAttribute("mensagemiguais", e.getMessage());
			return "redirect:/curso/novo";
		}
		
		
		//cursoService.salvar(curso);
		return "redirect:/curso/novo"; //na rota
	}
	
	
	@GetMapping("/listar")
	private ModelAndView listarCursos() {
		
		List<Curso> cursoList =  cursoService.buscarTodos();
		ModelAndView mv = new ModelAndView("/curso/listacursos.html");
		mv.addObject("cursos", cursoList);
		
		return mv;
	}

	


	
}
