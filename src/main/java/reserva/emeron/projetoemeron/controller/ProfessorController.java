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

import reserva.emeron.projetoemeron.model.Professor;
import reserva.emeron.projetoemeron.service.ProfessorService;

@Controller
@RequestMapping("/professor")
public class ProfessorController {
	
	
	
	@Autowired
	private ProfessorService professorService;
	
	
	@GetMapping("/novo")
	public ModelAndView capturarPagina (Professor professor) {
		
		ModelAndView mv = new ModelAndView ("professor/professorform.html");
		List<Professor> professorList = professorService.buscarTodosProfessores();
		
		mv.addObject("listprofessores", professorList);
		return mv;
		
	
	}
	@GetMapping("excluir/{id}")
	private String excluir(@PathVariable ("id") Long id , RedirectAttributes redirect) {
		
		try {
			
			professorService.excluir(id);
			redirect.addFlashAttribute("cursodeletado", "Professor Excluido  com Sucesso!");
		} catch (Exception e) {
			redirect.addFlashAttribute("cursocomreserva", "Este Professor Pertece a uma reserva!!");
			return "redirect:/professor/novo";
		}
		
		return "redirect:/professor/novo";
		
	}
	
	
	@GetMapping("{id}")
	private ModelAndView editProfessor(@PathVariable("id") Long id) {
		List<Professor> professorList = professorService.buscarTodosProfessores();

		ModelAndView mv = new ModelAndView("professor/professorform.html");
		mv.addObject("listprofessores", professorList);

		
		Professor professorEdit = this.professorService.findById(id);
		mv.addObject("professor", professorEdit);

	return mv;
	}
	
	
	
	@PostMapping("/salvar")
	public String salvarprofessor(@Valid Professor professor,  BindingResult result, RedirectAttributes redirect) {
		
		
		if (result.hasErrors()) {
			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatórios "); // mensagem na view
			return "redirect:/professor/novo"; // na rota
		}
		
		
		
		try {

			if (professor.getId() == null || (professor.getId() != null && professor.getId() <= 0)) {

				if (professorService.professorExiste(professor.getNome()) == false) {
					
					professorService.salvar(professor);
					
					redirect.addFlashAttribute("mensagemsucesso", "Professor  Adicionado com Sucesso!");

				} else {
					
					redirect.addFlashAttribute("mensagemiguais", "Professor  Já cadastrado!!");
				}

			} else {
				
				professorService.salvar(professor);
				
				redirect.addFlashAttribute("mensagemeditado", "Professor Editado com Sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("mensagemiguais", "Data de Nascimento não pode ser Nulo!");
			return "redirect:/professor/novo"; // na rota
			
		}
		return "redirect:/professor/novo"; // na rota
	}
	
	
	
	@GetMapping("/listar")
	private ModelAndView listarReserva() {
		
		List<Professor> professorList =  professorService.buscarTodosProfessores();
		ModelAndView mv = new ModelAndView("professor/listaprofessores.html");
		mv.addObject("professorlistas", professorList);
		
		return mv;
		
	}
	
	

}
