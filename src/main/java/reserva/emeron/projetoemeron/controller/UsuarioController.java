package reserva.emeron.projetoemeron.controller;

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

import reserva.emeron.projetoemeron.model.Usuario;
import reserva.emeron.projetoemeron.service.RoleService;
import reserva.emeron.projetoemeron.service.UsuarioService;

@Controller
@RequestMapping(value = "/usuario")
public class UsuarioController {
	
	
	@Autowired
	private UsuarioService usuarioService;
	
	@Autowired
	private RoleService roleService;
	
	
	
	
	@GetMapping("/novo")
	public ModelAndView novo(Usuario usuario) {
		Iterable<Usuario> usuariosIT = this.usuarioService.listarUsuarios();
		ModelAndView mv = new ModelAndView("usuario/usuarioform.html");
		mv.addObject("grupos", roleService.buscarTodosPerfil());
		mv.addObject("userList", usuariosIT );
		
		
		return mv;
	}
	
	@GetMapping("/novo13")
	private ModelAndView usuario() {
		
		Iterable<Usuario> usuariosIT = this.usuarioService.listarUsuarios();
		ModelAndView mv = new ModelAndView("usuario/usuarioform.html");
		mv.addObject("userList", usuariosIT );
		return mv;
	}
	
	
	
	
	
//	@GetMapping("/listar")
//	private ModelAndView listarUsuarios() {
//		
//		Iterable<Usuario> usuariosIT = this.usuarioService.listarUsuarios();
//		ModelAndView mv = new ModelAndView("usuario/usuarioform.html");
//		mv.addObject("userList", usuariosIT );
//		
//		return mv;
//		
//		
//	}
	
	/*
	 * @GetMapping("/listar") 
	 * private ModelAndView listarCursos() {
	 * 
	 * List<Curso> cursoList = cursoService.buscarTodos();
		ModelAndView mv = new  ModelAndView("/curso/listacursos.html"); 
		mv.addObject("usuarios",  usuarioLista);
	 * 
	 * 
	 * return mv; }
	 */
	
	
	
	
	@PostMapping("/salvar")
	private String salvar(@Valid Usuario usuario,  BindingResult result, RedirectAttributes redirect) {

		if (result.hasErrors()) {
			redirect.addFlashAttribute("mensagem", "Verifique os Campos Obrigatórios "); // mensagem na view
			return "redirect:/usuario/novo"; // na rota
		}

		try {

			if (usuario.getId() == null || (usuario.getId() != null && usuario.getId() <= 0)) {

				if (usuarioService.usuarioExiste(usuario.getLogin()) == false) {
					usuarioService.salvar(usuario);
					redirect.addFlashAttribute("mensagemsucesso", "Usuario Adicionado com Sucesso!");

				} else {
					redirect.addFlashAttribute("mensagemiguais", "Usuario Já cadastrado!!");
				}

			} 
				
			else {

				usuarioService.update(usuario);
				redirect.addFlashAttribute("mensagemeditado", "Usuario Editado com Sucesso!");
			}
		} catch (Exception e) {
			e.printStackTrace();
			
		}
		return "redirect:/usuario/novo"; // na rota
	}
	
	@GetMapping("{id}")
	private ModelAndView editCurso(@PathVariable("id") Long id) {
		Iterable<Usuario> usuariosIT = this.usuarioService.listarUsuarios();
		
		ModelAndView mv = new ModelAndView("usuario/usuarioform.html");
		mv.addObject("userList", usuariosIT);
		mv.addObject("grupos", roleService.buscarTodosPerfil());
		
		
		Usuario userEdit = this.usuarioService.findById(id);

		mv.addObject("usuario", userEdit);

		return mv;
	}
	
	

}
