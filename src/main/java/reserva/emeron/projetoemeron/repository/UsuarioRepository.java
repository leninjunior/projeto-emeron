package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reserva.emeron.projetoemeron.model.Usuario;


@Repository
@Transactional
public interface UsuarioRepository  extends JpaRepository<Usuario, Long>{

	
		@Query("select u from Usuario u where u.login =:login")
		Usuario findUserByLogin(String login);
		
		
		
		@Query(value = "select count(1) > 0 as existe from usuario where login = ?", nativeQuery = true)
		public boolean usuarioJaExiste(String login);
		
	
		
		
		
}
