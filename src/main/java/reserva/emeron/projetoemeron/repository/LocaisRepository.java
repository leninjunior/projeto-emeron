package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import reserva.emeron.projetoemeron.model.Locais;

@Repository
public interface LocaisRepository  extends JpaRepository<Locais, Long> {
	
	
	

}
