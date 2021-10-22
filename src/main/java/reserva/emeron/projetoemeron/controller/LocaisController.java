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

import reserva.emeron.projetoemeron.model.Locais;
import reserva.emeron.projetoemeron.service.LocaisService;

@Controller
@RequestMapping("/local")
public class LocaisController {
	
	
	@Autowired
	private LocaisService locaisService;
	
	
	@GetMapping("/novo")
	public ModelAndView indexLocal( Locais local) {
		
		ModelAndView mv = new ModelAndView("locais/localform.html");
		List<Locais> localList = locaisService.buscarTodosLocais();

		mv.addObject("listlocais", localList);

			
		return mv;
		
	}
	

	@PostMapping("/salvar")
	public String salvarLocais(@Valid Locais local,  BindingResult result, RedirectAttributes redirect) {
		
		
		if (result.hasErrors()) {
			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatórios "); // mensagem na view
			return "redirect:/local/novo"; // na rota
		}
		
		
		
		try {

			if (local.getId() == null || (local.getId() != null && local.getId() <= 0)) {

				if (locaisService.locaisExiste(local.getNomeLocal()) == false) {
					
					locaisService.salvarLocal(local);
					
					redirect.addFlashAttribute("mensagemsucesso", "Local  Adicionado com Sucesso!");

				}

			} else {
				
				locaisService.salvarLocal(local);
				
				redirect.addFlashAttribute("mensagemeditado", "Local Editado com Sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			redirect.addFlashAttribute("mensagemiguais", "Local  Já cadastrado!!");
		}
		return "redirect:/local/novo"; // na rota
	}
	
	
	@GetMapping("{id}")
	private ModelAndView editCurso(@PathVariable("id") Long id) {
		List<Locais> localList = locaisService.buscarTodosLocais();

		ModelAndView mv = new ModelAndView("locais/localform.html");
		mv.addObject("listlocais", localList);

		
		Locais locaisEdit = this.locaisService.findById(id);
		mv.addObject("locais", locaisEdit);

	return mv;
	}
	
	@GetMapping("excluir/{id}")
	private String excluir(@PathVariable ("id") Long id , RedirectAttributes redirect) {
		
		try {
			
			locaisService.excluir(id);
			redirect.addFlashAttribute("cursodeletado", "Local Excluido  com Sucesso!");
		} catch (Exception e) {
			redirect.addFlashAttribute("cursocomreserva", "Este Local Pertece a uma reserva!!");
			return "redirect:/local/novo";
		}
		
		return "redirect:/local/novo";
		
	}

	
	
}
