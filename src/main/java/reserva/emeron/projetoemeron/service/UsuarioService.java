package reserva.emeron.projetoemeron.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import reserva.emeron.projetoemeron.model.Usuario;
import reserva.emeron.projetoemeron.repository.UsuarioRepository;


@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	

	
	public Usuario getUser() {
		Object object =  SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		
		String username  = ((UserDetails) object).getUsername();
		
		 Usuario usuario =  usuarioRepository.findUserByLogin(username);
		
		System.out.println(">>>>>>>>>>>>>>" +usuario);
		
		return usuario ;
	}
	
	
	public void salvar(Usuario usuario) {
		
		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		this.usuarioRepository.save(usuario);
		//System.out.println("senha  criptografada >>>>>>>>>>>>>>>>>> " + usuario.getSenha());
	}
	
	
	
	public boolean usuarioExiste(String login) {

		return usuarioRepository.usuarioJaExiste(login);

	}
	
	
	 public Iterable<Usuario> listarUsuarios(){
		 
		 Iterable<Usuario> userList =  this.usuarioRepository.findAll();
		 
		return userList;
		 
	 }
	 



	public void update(Usuario usuario) {
		usuario.setSenha(this.passwordEncoder.encode(usuario.getSenha()));
		this.usuarioRepository.save(usuario);
	//	System.out.println("senha  criptografada  update>>>>>>>>>>>>>>>>>> " + usuario.getSenha());
	
	}
	
	public Usuario findById(Long id) {

		return this.usuarioRepository.findById(id).get();
	}

	public Integer countUsuarios() {
		 return (int) usuarioRepository.count();
		 
		}
}
