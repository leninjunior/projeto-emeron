package reserva.emeron.projetoemeron.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import reserva.emeron.projetoemeron.model.Professor;

@Repository
public interface ProfessorRepository extends JpaRepository<Professor, Long> {

	
	
	
	@Query(value = "select count(1) > 0 as existe from professor where nome = ?", nativeQuery = true)
	boolean professorJaExiste(String nome);
	

	
	

}
