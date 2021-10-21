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
import reserva.emeron.projetoemeron.model.Locais;
import reserva.emeron.projetoemeron.model.Reserva;
import reserva.emeron.projetoemeron.model.Usuario;
import reserva.emeron.projetoemeron.service.CursoService;
import reserva.emeron.projetoemeron.service.LocaisService;
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
	
	@GetMapping("/novo")
	private ModelAndView reserva() {
		 
		Usuario usuario = usuarioService.getUser();	 
		ModelAndView mv = new ModelAndView("reserva/reservaform.html");
		
		List<Reserva> reservaList = this.reservaService.buscarTodos();
		List<Curso> cursoList = this.cursoService.buscarTodosCursos();
		
		List<Locais> locaisList =  this.locaisService.buscarTodosLocais();
		
		
		
		mv.addObject("reservalist", reservaList);
		mv.addObject("usuarioid", usuario.getId());
		mv.addObject("cursolist", cursoList);
		mv.addObject("locaislist", locaisList);
		
		
	
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
		
		
		try {

			if (reserva.getId() == null || (reserva.getId() != null && reserva.getId() <= 0)) {

				if (reservaService.reservaExiste(reserva.getNome()) == false) {
					
					reservaService.salvarDados(reserva);
			
					redirect.addFlashAttribute("mensagemsucesso", "Reserva  Adicionado com Sucesso!");

				}
				
				
				
				} else {
					reservaService.updateTeste(reserva); 
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
			redirect.addFlashAttribute("mensagemiguais", "Reserva  Já cadastrado(EXCEPTION)!!");
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
	private ModelAndView editReserva(@PathVariable("id") Long id) {
		
		Reserva reservaEdit = this.reservaService.findById(id);
		ModelAndView mv = new ModelAndView("reserva/reservaform.html");
		

		List<Curso> cursoList = this.cursoService.buscarTodosCursos();
		List<Reserva> reservaList = this.reservaService.buscarTodos();
		List<Locais> locaisList =  this.locaisService.buscarTodosLocais();
		
		
		
		mv.addObject("cursolist", cursoList);
		mv.addObject("locaislist", locaisList);
		mv.addObject("reservalist", reservaList);
		
		
		
		
		

		mv.addObject("reserva", reservaEdit);

		return mv;
	}
	
	
	  @GetMapping("/minhasreservas") 
	  private ModelAndView  listarReservaPorUsuario() {
	  
		  Usuario usuario = usuarioService.getUser();	 
		  
	  List<Reserva> reservaPorIdUsuario = reservaService.buscarReservaPorUsuario(usuario);
	  
	  
	  ModelAndView mv = new ModelAndView("reserva/minhasreservas.html");
	  
	  
	  mv.addObject("reservalist", reservaPorIdUsuario);
	  
	  
	  
	  
	  
	  
	  
	  return mv; }
	 
	

}



