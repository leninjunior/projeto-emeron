package reserva.emeron.projetoemeron.security;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import reserva.emeron.projetoemeron.model.Usuario;

public class UsuarioLogado extends User {

	@Autowired
	private Usuario usuario;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	public UsuarioLogado(Usuario usuario,
			Collection<? extends GrantedAuthority> authorities) {
		super(usuario.getLogin(), usuario.getPassword(), authorities);
		this.usuario = usuario;
		// TODO Auto-generated constructor stub
	}


	public Usuario getUsuario() {
		return usuario;
	}



	
	
}
