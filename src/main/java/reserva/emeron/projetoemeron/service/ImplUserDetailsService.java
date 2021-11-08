package reserva.emeron.projetoemeron.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import reserva.emeron.projetoemeron.model.Usuario;
import reserva.emeron.projetoemeron.repository.UsuarioRepository;
import reserva.emeron.projetoemeron.security.UsuarioLogado;


@Service
@Transactional
public class ImplUserDetailsService  implements UserDetailsService {

	@Autowired
	private UsuarioRepository usuarioRepository;
	
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		
		/* Usuario usuario = usuarioRepository.findUserByLogin(username); */
		
		Optional<Usuario> usuarioOptional = usuarioRepository.findByLoginAndAtivo(username);
		Usuario usuario = usuarioOptional.orElseThrow(() -> new UsernameNotFoundException("Usuário e/ou senha incorretos"));
		 
		
			
		
			 return new UsuarioLogado(usuario, usuario.getAuthorities());
		
		 
		 
		
	}

	
	
	
	
	
}
