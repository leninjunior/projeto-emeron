package reserva.emeron.projetoemeron.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import reserva.emeron.projetoemeron.model.Curso;

@Repository
public interface CursoRepository extends JpaRepository<Curso, Long>{
	
	
	List<Curso> findByNomeIgnoreCase(String nome);

	 
	
	@Query(value = "select count(1) > 0 as existe from curso where nome = ?", nativeQuery = true)
	public boolean cursoJaExiste(String nome);


	
	
}
