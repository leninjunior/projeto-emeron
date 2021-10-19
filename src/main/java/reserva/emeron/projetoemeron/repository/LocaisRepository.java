package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import reserva.emeron.projetoemeron.model.Locais;

@Repository
public interface LocaisRepository  extends JpaRepository<Locais, Long> {

	
	
	
	
	@Query(value = "select count(1) > 0 as existe from locais where nome_local = ?", nativeQuery = true)
	boolean localExiste(String nome);
	
	
	

}
