package reserva.emeron.projetoemeron.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
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
		
		

		
		@Query(value = "select * from usuario where tipo_status ='INATIVO'", nativeQuery = true)
		public String usuarioInativo(String string);
		
		
		@Query("select u from Usuario u where u.login =:login AND u.ativo=true")
		Optional<Usuario> findByLoginAndAtivo(String login);
		
	  	@Modifying
		@Query(value = "update usuario set ativo='false' where id=?1", nativeQuery = true)
		public void inativarUsuario(Usuario id);
	  	
	 	@Modifying
			@Query(value = "update usuario set ativo='true' where id=?1", nativeQuery = true)
			public void ativarUsuario(Usuario id);
		/*
		 * @Query(value = " select\r\n" + "        usuario.id,\r\n" +
		 * "        usuario.login,\r\n" + "        usuario.senha \r\n" + "    from\r\n"
		 * + "        usuario usuario", nativeQuery = true) public List<Usuario>
		 * editUsuario();
		 */
		
		
}
