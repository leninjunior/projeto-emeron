package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import reserva.emeron.projetoemeron.model.Role;


@Repository
@Transactional
public interface RoleRepository  extends JpaRepository<Role, Long>{
	
	
	

}
